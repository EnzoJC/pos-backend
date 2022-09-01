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
public class DataSheetId implements Serializable {
    private static final long serialVersionUID = 1006979221300964973L;
    @Column(name = "type_detail_id", nullable = false)
    private Integer typeDetailId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DataSheetId entity = (DataSheetId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.typeDetailId, entity.typeDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, typeDetailId);
    }

}