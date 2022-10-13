package pe.edu.upn.pos.dto.request;

public record SignUpRequest(
    String givenNames,
    String firstLastName,
    String secondLastName,
    int genderId,
    int documentTypeId,
    String documentNumber,
    String dateOfBirth,
    String phone,
    String address,
    int nationalityId,
    String email,
    int roleId
    ) {
}
