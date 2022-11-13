package pe.edu.upn.pos.dto.response;

public record LoginResponse(String token, String type, String username, String role) {
    public LoginResponse(String token, String username, String role) {
        this(token, "Bearer", username, role);
    }
}