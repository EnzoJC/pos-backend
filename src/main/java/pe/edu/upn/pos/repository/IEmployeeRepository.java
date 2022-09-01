package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
}