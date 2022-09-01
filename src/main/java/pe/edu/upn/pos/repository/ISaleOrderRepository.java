package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.SaleOrder;

public interface ISaleOrderRepository extends JpaRepository<SaleOrder, Integer> {
}