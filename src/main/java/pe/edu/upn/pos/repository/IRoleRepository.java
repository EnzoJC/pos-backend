package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}