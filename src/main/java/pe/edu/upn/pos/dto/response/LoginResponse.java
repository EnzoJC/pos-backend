package pe.edu.upn.pos.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;

    public LoginResponse(Long id, String token, String username, String email) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
    }

}
