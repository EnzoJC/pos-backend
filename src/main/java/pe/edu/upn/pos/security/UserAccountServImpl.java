package pe.edu.upn.pos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.request.SignUpRequest;
import pe.edu.upn.pos.entity.*;
import pe.edu.upn.pos.exception.GlobalDateFormatException;
import pe.edu.upn.pos.exception.NoDataFoundException;
import pe.edu.upn.pos.repository.*;
import pe.edu.upn.pos.service.IUserAccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class UserAccountServImpl implements UserDetailsService, IUserAccountService {

    @Value("${spring.profiles.active}")
    private String currentProfile;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IGenderRepository genderRepository;

    @Autowired
    private IDocumentTypeRepository documentTypeRepository;

    @Autowired
    private INationalityRepository nationalityRepository;

    @Autowired
    private IRoleRepository roleRepository;

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
    public void save(SignUpRequest signUpRequest) {
        Gender gender = genderRepository.findById(signUpRequest.getGenderId())
                .orElseThrow(() -> new NoDataFoundException("genderId", String.valueOf(signUpRequest.getGenderId())));

        DocumentType documentType = documentTypeRepository.findById(signUpRequest.getDocumentTypeId())
                .orElseThrow(() -> new NoDataFoundException("documentTypeId", String.valueOf(signUpRequest.getDocumentTypeId())));

        Nationality nationality = nationalityRepository.findById(signUpRequest.getNationalityId())
                .orElseThrow(() -> new NoDataFoundException("nationalityId", String.valueOf(signUpRequest.getNationalityId())));

        Role role = roleRepository.findById(signUpRequest.getRoleId())
                .orElseThrow(() -> new NoDataFoundException("roleId", String.valueOf(signUpRequest.getRoleId())));

        LocalDate dateOfBirth;

        try {
            dateOfBirth = LocalDate.parse(signUpRequest.getDateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) { throw new GlobalDateFormatException("dateOfBirth"); }

        UserAccount userAccount = UserAccount.builder()
                .username(signUpRequest.getUsername())
                .passwordHash(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .role(role)
                .build();

        Employee employee = Employee.builder()
                .userAccount(userAccount)
                .givenNames(signUpRequest.getGivenNames())
                .firstLastName(signUpRequest.getFirstLastName())
                .gender(gender)
                .documentType(documentType)
                .documentNumber(signUpRequest.getDocumentNumber())
                .dateOfBirth(dateOfBirth)
                .phone(signUpRequest.getPhone())
                .address(signUpRequest.getAddress())
                .nationality(nationality)
                .build();

        userAccountRepository.save(userAccount);
        employeeRepository.save(employee);
    }
}
