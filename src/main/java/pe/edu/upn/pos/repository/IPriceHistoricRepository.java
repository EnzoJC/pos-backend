package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.PriceHistoric;

public interface IPriceHistoricRepository extends JpaRepository<PriceHistoric, Integer> {
}