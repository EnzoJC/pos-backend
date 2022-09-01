package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.AttributeDetail;

public interface IAttributeDetailRepository extends JpaRepository<AttributeDetail, Integer> {
}