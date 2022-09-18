package pe.edu.upn.pos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount {

    private static final String REGEX_FOR_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Employee employee;

    @Column(name = "username", nullable = false, length = 50)
    @NotEmpty
    private String username;

    @Column(name = "password_hash", nullable = false, length = 250)
    @NotEmpty
    private String passwordHash;

    @Column(name = "password_salt", length = 100)
    private String passwordSalt;

    @Column(name = "email", nullable = false, length = 50)
    @Email(regexp = REGEX_FOR_EMAIL)
    private String email;

    @Column(name = "confirmation_token", length = 100)
    private String confirmationToken;

    @Column(name = "token_generation_time")
    private Instant tokenGenerationTime;

    @Column(name = "recovery_token", length = 100)
    private String recoveryToken;

    @Column(name = "recovery_token_time")
    private Instant recoveryTokenTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email_validation_status_id", nullable = false)
    private EmailValidationStatus emailValidationStatus;

}