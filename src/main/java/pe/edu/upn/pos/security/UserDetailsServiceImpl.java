package pe.edu.upn.pos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upn.pos.entity.UserAccount;
import pe.edu.upn.pos.repository.IUserAccountRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${current.environment}")
    private String currentEnvirontment;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (currentEnvirontment.equals("dev")) {
            Map<String, String> users = new HashMap<>();
            users.put("test", passwordEncoder.encode("test"));
            if (users.containsKey(username)) {
                return new UserDetailsImpl(username, "test@test.com", users.get("test"), new ArrayList<>());
            }
            throw new UsernameNotFoundException(username);
        } else {
            UserAccount userAccount = userAccountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            return UserDetailsImpl.build(userAccount);
        }
    }
}
