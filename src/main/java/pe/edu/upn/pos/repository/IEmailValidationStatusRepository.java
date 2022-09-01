package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.EmailValidationStatus;

public interface IEmailValidationStatusRepository extends JpaRepository<EmailValidationStatus, Integer> {
}