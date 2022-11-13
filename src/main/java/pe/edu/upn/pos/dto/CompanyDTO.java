package pe.edu.upn.pos.dto;

public record CompanyDTO(
        String name,
        String ruc,
        String address,
        String email,
        String phone1,
        String phone2,
        String phone3,
        String url,
        String image
) {
}
