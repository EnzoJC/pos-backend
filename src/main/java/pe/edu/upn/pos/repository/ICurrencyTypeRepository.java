package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upn.pos.entity.CurrencyType;

public interface ICurrencyTypeRepository extends JpaRepository<CurrencyType, Integer> {

    Boolean existsCurrencyTypeByCurrency(String currency);
}