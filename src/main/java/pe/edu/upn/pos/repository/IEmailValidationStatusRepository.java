package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.EmailValidationStatus;

import java.util.Optional;

public interface IEmailValidationStatusRepository extends JpaRepository<EmailValidationStatus, Integer> {

    Boolean existsEmailValidationStatusByStatus(String status);

    Optional<EmailValidationStatus> findByStatus(String status);
}