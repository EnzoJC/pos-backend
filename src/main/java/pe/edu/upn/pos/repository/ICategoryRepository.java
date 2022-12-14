package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}