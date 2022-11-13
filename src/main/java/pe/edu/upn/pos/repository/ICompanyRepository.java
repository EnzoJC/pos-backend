package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Company;

import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findFirstByOrderByIdAsc();
}