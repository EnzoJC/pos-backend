------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION array_diff(array1 anyarray, array2 anyarray)
    RETURNS anyarray
    LANGUAGE sql
    IMMUTABLE AS
$$
SELECT COALESCE(ARRAY_AGG(elem), '{}')
FROM UNNEST(array1) elem
WHERE elem <> ALL (array2)
$$;
------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION audit() RETURNS TRIGGER AS
$audit$
DECLARE
    row_pk_name TEXT   = (SELECT attname
                          FROM pg_index
                                   JOIN pg_attribute ON
                                      attrelid = indrelid
                                  AND attnum = ANY (indkey)
                          WHERE indrelid = TG_RELID
                            AND indisprimary);
    columns     TEXT[] = array_diff(
            ARRAY(
                    SELECT attname
                    FROM pg_attribute
                    WHERE attrelid = TG_RELID
                      AND attstattarget = -1
                ),
            ARRAY(SELECT attname
                  FROM pg_index
                           JOIN pg_attribute ON
                              attrelid = indrelid
                          AND attnum = ANY (indkey)
                  WHERE indrelid = TG_RELID
                    AND indisprimary)
        );
    i           TEXT;
BEGIN
    INSERT INTO public.audit_log_header (date_time, table_name, row_pk, username, "action")
    VALUES (NOW() AT TIME ZONE ('utc+5'), TG_TABLE_NAME, (ROW_TO_JSON(NEW) ->> row_pk_name), SESSION_USER, TG_OP);

    FOREACH i IN ARRAY columns
        LOOP
            IF (ROW_TO_JSON(NEW) ->> i IS DISTINCT FROM ROW_TO_JSON(OLD) ->> i) THEN
                INSERT INTO public.audit_log_value (column_name, previous_value, new_value, audit_log_header_id)
                VALUES (i, ROW_TO_JSON(OLD) ->> i, ROW_TO_JSON(NEW) ->> i, (SELECT id
                                                                            FROM public.audit_log_header
                                                                            ORDER BY id DESC
                                                                            LIMIT 1));
            END IF;
        END LOOP;
    RETURN NEW;
END;
$audit$ LANGUAGE plpgsql;
------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER IF EXISTS audit_trigger_insert ON public.attribute_detail;
CREATE TRIGGER audit_trigger_insert
    AFTER INSERT
    ON attribute_detail
    FOR EACH ROW
EXECUTE PROCEDURE audit();

DROP TRIGGER IF EXISTS audit_trigger_update ON public.attribute_detail;
CREATE TRIGGER audit_trigger_update
    AFTER UPDATE
    ON attribute_detail
    FOR EACH ROW
EXECUTE PROCEDURE audit();

DROP TRIGGER IF EXISTS audit_trigger_update ON public.attribute_detail;
CREATE TRIGGER audit_trigger_update
    AFTER DELETE
    ON attribute_detail
    FOR EACH ROW
EXECUTE PROCEDURE audit();