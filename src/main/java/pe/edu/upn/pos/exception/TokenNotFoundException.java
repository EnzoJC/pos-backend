package pe.edu.upn.pos.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String token) {
        super(String.format("The next token %s is not found", token));
    }
}
