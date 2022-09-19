package pe.edu.upn.pos.exception;

public class ValueRepeatedException extends RuntimeException {
    public ValueRepeatedException(String field, String value) {
        super(String.format("The field %s with value %s is already registered", field, value));
    }
}
