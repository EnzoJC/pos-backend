package pe.edu.upn.pos.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.request.SignUpRequest;
import pe.edu.upn.pos.dto.response.VerificationEmailResponse;
import pe.edu.upn.pos.email.IEmailSender;
import pe.edu.upn.pos.entity.*;
import pe.edu.upn.pos.exception.GlobalDateFormatException;
import pe.edu.upn.pos.exception.NoDataFoundException;
import pe.edu.upn.pos.exception.ValueRepeatedException;
import pe.edu.upn.pos.repository.*;
import pe.edu.upn.pos.service.IUserAccountService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountServImpl implements UserDetailsService, IUserAccountService {

    @Value("${spring.profiles.active}")
    private String currentProfile;

    private final PasswordEncoder passwordEncoder;

    private final IUserAccountRepository userAccountRepository;

    private final IEmployeeRepository employeeRepository;

    private final IGenderRepository genderRepository;

    private final IDocumentTypeRepository documentTypeRepository;

    private final INationalityRepository nationalityRepository;

    private final IRoleRepository roleRepository;

    private final IEmailSender emailSender;

    public UserAccountServImpl(PasswordEncoder passwordEncoder, IUserAccountRepository userAccountRepository,
                               IEmployeeRepository employeeRepository, IGenderRepository genderRepository,
                               IDocumentTypeRepository documentTypeRepository, INationalityRepository nationalityRepository,
                               IRoleRepository roleRepository, IEmailSender emailSender) {
        this.passwordEncoder = passwordEncoder;
        this.userAccountRepository = userAccountRepository;
        this.employeeRepository = employeeRepository;
        this.genderRepository = genderRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.nationalityRepository = nationalityRepository;
        this.roleRepository = roleRepository;
        this.emailSender = emailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(userAccount);
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    @Override
    public Optional<UserAccount> findByUsernameAndAndIsEmailVerifiedIsTrue(String username) {
        return userAccountRepository.findByUsernameAndAndIsEmailVerifiedIsTrue(username);
    }

    @Override
    public void save(SignUpRequest signUpRequest) {
        Gender gender = genderRepository.findById(signUpRequest.genderId())
                .orElseThrow(() -> new NoDataFoundException("genderId", String.valueOf(signUpRequest.genderId())));

        DocumentType documentType = documentTypeRepository.findById(signUpRequest.documentTypeId())
                .orElseThrow(() -> new NoDataFoundException("documentTypeId", String.valueOf(signUpRequest.documentTypeId())));

        Nationality nationality = nationalityRepository.findById(signUpRequest.nationalityId())
                .orElseThrow(() -> new NoDataFoundException("nationalityId", String.valueOf(signUpRequest.nationalityId())));

        Role role = roleRepository.findById(signUpRequest.roleId())
                .orElseThrow(() -> new NoDataFoundException("roleId", String.valueOf(signUpRequest.roleId())));

        if (userAccountRepository.existsUserAccountByEmail(signUpRequest.email())) {
            throw new ValueRepeatedException("email", signUpRequest.email());
        }

        if (employeeRepository.existsEmployeeByPhone(signUpRequest.phone())) {
            throw new ValueRepeatedException("phone", signUpRequest.phone());
        }

        if (employeeRepository.existsEmployeeByDocumentNumber(signUpRequest.documentNumber())) {
            throw new ValueRepeatedException("documentNumber", signUpRequest.documentNumber());
        }

        LocalDate dateOfBirth;

        try {
            dateOfBirth = LocalDate.parse(signUpRequest.dateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new GlobalDateFormatException("dateOfBirth");
        }

        String confirmationToken = UUID.randomUUID().toString();

        UserAccount userAccount = UserAccount.builder()
                .username(signUpRequest.documentNumber())
                .passwordHash(passwordEncoder.encode(signUpRequest.documentNumber()))
                .email(signUpRequest.email())
                .role(role)
                .isEmailVerified(false)
                .confirmationToken(confirmationToken)
                .tokenGenerationTime(LocalDateTime.now().plusHours(6)) // TODO: this value should be in application.properties
                .build();

        Employee employee = Employee.builder()
                .userAccount(userAccount)
                .givenNames(signUpRequest.givenNames())
                .firstLastName(signUpRequest.firstLastName())
                .secondLastName(signUpRequest.secondLastName())
                .gender(gender)
                .documentType(documentType)
                .documentNumber(signUpRequest.documentNumber())
                .dateOfBirth(dateOfBirth)
                .phone(signUpRequest.phone())
                .address(signUpRequest.address())
                .nationality(nationality)
                .isActive(true)
                .build();

        userAccountRepository.save(userAccount);
        emailSender.sendConfirmationToken(signUpRequest, confirmationToken, "email-template");
        employeeRepository.save(employee);
    }

    @Override
    public VerificationEmailResponse verifyEmail(String token) {
        UserAccount userAccount = userAccountRepository.findByConfirmationToken(token).orElseThrow(() -> new NoDataFoundException("token", token));

        if (userAccount.getTokenGenerationTime().isAfter(LocalDateTime.now())) {
            userAccount.setIsEmailVerified(true);
            userAccountRepository.save(userAccount);
            return new VerificationEmailResponse("success", "Your email has been verified successfully");
        } else {
            return new VerificationEmailResponse("error", "Your token has expired. Please request a new one");
        }
    }
}
