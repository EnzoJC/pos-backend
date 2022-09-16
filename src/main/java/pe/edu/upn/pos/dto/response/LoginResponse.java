package pe.edu.upn.pos.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class LoginResponse extends RepresentationModel<LoginResponse> {
    private String token;
    private String type = "Bearer";
    private String username;

    public LoginResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

}
