package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.GrantedPermission;
import pe.edu.upn.pos.entity.GrantedPermissionId;

public interface IGrantedPermissionRepository extends JpaRepository<GrantedPermission, GrantedPermissionId> {
}