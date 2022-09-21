package pe.edu.upn.pos.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String givenNames;
    private String firstLastName;
    private String secondLastName = "";
    private int genderId;
    private int documentTypeId;
    private String documentNumber;
    private String dateOfBirth;
    private String phone;
    private String address = "";
    private int nationalityId;
    private String username;
    private String password;
    private String email;
    private int roleId;
}
