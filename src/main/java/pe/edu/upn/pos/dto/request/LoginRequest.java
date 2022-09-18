package pe.edu.upn.pos.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = "The username field must not be empty")
    private String username;
    @NotEmpty(message = "The password field must not be empty")
    private String password;
}
