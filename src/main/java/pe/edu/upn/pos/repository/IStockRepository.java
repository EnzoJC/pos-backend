package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Stock;

public interface IStockRepository extends JpaRepository<Stock, Integer> {
}