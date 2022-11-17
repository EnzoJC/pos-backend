package pe.edu.upn.pos.dto.response;

import java.time.LocalDate;

public record EmployeeResponse(
        int id,
        String username,
        String email,
        String role,
        String givenNames,
        String firstLastName,
        String secondLastName,
        String gender,
        String documentType,
        String documentNumber,
        LocalDate dateOfBirth,
        String phone,
        String address,
        String nationality,
        boolean isActive
) {
}