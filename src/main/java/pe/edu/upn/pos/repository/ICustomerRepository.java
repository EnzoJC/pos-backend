package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}