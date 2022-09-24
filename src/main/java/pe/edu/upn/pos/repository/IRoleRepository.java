package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upn.pos.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Long countByRoleLike(String role);

    Boolean existsRoleByRole(String role);
}