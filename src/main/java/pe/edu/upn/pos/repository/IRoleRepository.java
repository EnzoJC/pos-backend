package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Role;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Long countByNameLike(String role);

    Boolean existsRoleByName(String role);

    Optional<Role> findByName(String role);
}