package pe.edu.upn.pos.exception;

public class GlobalDateFormatException extends RuntimeException {

    public GlobalDateFormatException(String field) {
        super(String.format("The date format in field %s is invalid. The correct format should be with the following pattern: dd/MM/yyyy", field));
    }
}
