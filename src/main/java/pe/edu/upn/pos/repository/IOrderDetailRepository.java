package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.OrderDetail;
import pe.edu.upn.pos.entity.OrderDetailId;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}