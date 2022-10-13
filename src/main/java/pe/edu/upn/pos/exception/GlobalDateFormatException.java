package pe.edu.upn.pos.exception;

public class GlobalDateFormatException extends RuntimeException {

    public GlobalDateFormatException(String field) {
        super(String.format("El formato de la fecha en el campo %s no es válido. El formato correcto debe ser con el siguiente formato: dd/MM/yyyy", field));
    }
}
