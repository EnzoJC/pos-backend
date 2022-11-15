package pe.edu.upn.pos.dto;

import java.util.Set;

public record RoleDTO(
        Integer id,
        String name,
        Set<PermissionDTO> permissions
) {
}
