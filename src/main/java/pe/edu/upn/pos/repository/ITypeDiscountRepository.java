package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.TypeDiscount;

public interface ITypeDiscountRepository extends JpaRepository<TypeDiscount, Integer> {
}