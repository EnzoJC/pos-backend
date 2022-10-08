package pe.edu.upn.pos.dto.response;

public record LoginResponse(String token, String type, String username) {
    public LoginResponse (String token, String username) {
        this(token, "Bearer", username);
    }
}