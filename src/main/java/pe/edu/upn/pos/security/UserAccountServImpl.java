package pe.edu.upn.pos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upn.pos.dto.request.SignUpRequest;
import pe.edu.upn.pos.entity.Permission;
import pe.edu.upn.pos.entity.Role;
import pe.edu.upn.pos.entity.UserAccount;
import pe.edu.upn.pos.repository.IUserAccountRepository;
import pe.edu.upn.pos.service.IUserAccountService;

import java.util.*;

@Service
public class UserAccountServImpl implements UserDetailsService, IUserAccountService {

    @Value("${spring.profiles.active}")
    private String currentProfile;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (currentProfile.equals("dev")) {
            Map<String, String> users = new HashMap<>();
            users.put("test", passwordEncoder.encode("test"));
            if (users.containsKey(username)) {
                return new UserDetailsImpl(username,
                        "test@test.com",
                        users.get("test"),
                        List.of(new Role(
                                        1,
                                        "ROLE_USER",
                                        new HashSet<>(List.of(new Permission(1, "READ")))
                                )
                        )
                );
            }
            throw new UsernameNotFoundException(username);
        } else {
            UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            return UserDetailsImpl.build(userAccount);
        }
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    @Override
    public void save(SignUpRequest signUpRequest) {
        UserAccount userAccount = new UserAccount();

    }
}
