package pe.edu.upn.pos.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "user_account",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_account_ak_1", columnNames = "username"),
                @UniqueConstraint(name = "user_account_ak_2", columnNames = "email"),
        },
        indexes = {
                @Index(name = "user_account_idx_1", columnList = "role_id", unique = true),
        }
)
public class UserAccount {

    private static final String REGEX_FOR_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;

    @Column(name = "password_hash", nullable = false, length = 250)
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String passwordHash;

    @Column(name = "email", nullable = false, length = 50)
    @Email(regexp = REGEX_FOR_EMAIL, message = "El email no es válido")
    private String email;

    @Column(name = "confirmation_token", length = 100)
    private String confirmationToken;

    @Column(name = "is_email_verified", nullable = false)
    private Boolean isEmailVerified;

    @Column(name = "token_generation_time")
    private LocalDateTime tokenGenerationTime;

    @Column(name = "recovery_token", length = 100)
    private String recoveryToken;

    @Column(name = "recovery_token_time")
    private LocalDateTime recoveryTokenTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}