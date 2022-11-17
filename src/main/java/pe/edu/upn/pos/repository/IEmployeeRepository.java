package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Employee;
import pe.edu.upn.pos.entity.UserAccount;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    Boolean existsEmployeeByDocumentNumber(String documentNumber);

    Boolean existsEmployeeByPhone(String phone);

    Optional<Employee> findByDocumentNumber(String documentNumber);

    Optional<Employee> findByUserAccount(UserAccount userAccount);
}