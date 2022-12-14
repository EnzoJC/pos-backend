package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Integer> {

    Boolean existsPermissionByName(String permission);

    Long countByNameLike(String permission);
}