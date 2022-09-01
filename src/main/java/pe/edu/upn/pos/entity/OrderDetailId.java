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
public class OrderDetailId implements Serializable {
    private static final long serialVersionUID = -1029360286803407845L;
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "sale_order_id", nullable = false)
    private Integer saleOrderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetailId entity = (OrderDetailId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.saleOrderId, entity.saleOrderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, saleOrderId);
    }

}