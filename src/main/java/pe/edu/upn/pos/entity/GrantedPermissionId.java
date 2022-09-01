package pe.edu.upn.pos.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class GrantedPermissionId implements Serializable {
    private static final long serialVersionUID = -1578889344959254526L;
    @Column(name = "permission_id", nullable = false)
    private Integer permissionId;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GrantedPermissionId entity = (GrantedPermissionId) o;
        return Objects.equals(this.permissionId, entity.permissionId) &&
                Objects.equals(this.roleId, entity.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId, roleId);
    }

}