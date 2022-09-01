package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Brand;

public interface IBrandRepository extends JpaRepository<Brand, Integer> {
}