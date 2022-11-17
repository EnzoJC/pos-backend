package pe.edu.upn.pos.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String field, String value) {
        super(String.format("The field %s with value %s is not found", field, value));
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}
