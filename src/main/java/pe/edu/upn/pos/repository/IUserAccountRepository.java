package pe.edu.upn.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upn.pos.entity.UserAccount;

public interface IUserAccountRepository extends JpaRepository<UserAccount, Integer> {
}