package pe.edu.upn.pos.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.upn.pos.entity.UserAccount;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = -5970265679105453033L;

    private Long id;

    private String username;

    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;

    public UserDetailsImpl(String username, String email, String passwordHash, List<GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = passwordHash;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public static UserDetailsImpl build(UserAccount userAccount) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userAccount.getEmployee().getRole().getRole()));
        return new UserDetailsImpl(userAccount.getUsername(), userAccount.getEmail(), userAccount.getPasswordHash(), authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetailsImpl user = (UserDetailsImpl) o;
        return id.equals(user.id);
    }
}
