package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Discount;

public interface IDiscountRepository extends JpaRepository<Discount, Integer> {
}