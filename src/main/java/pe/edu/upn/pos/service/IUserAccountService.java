package pe.edu.upn.pos.service;

import pe.edu.upn.pos.dto.request.SignUpRequest;
import pe.edu.upn.pos.dto.response.VerificationEmailResponse;
import pe.edu.upn.pos.entity.UserAccount;

import java.util.Optional;

public interface IUserAccountService {

    Optional<UserAccount> findByUsername(String username);

    Optional<UserAccount> findByUsernameAndAndIsEmailVerifiedIsTrue(String username);

    void save(SignUpRequest signUpRequest);

    VerificationEmailResponse verifyEmail(String token);
}
