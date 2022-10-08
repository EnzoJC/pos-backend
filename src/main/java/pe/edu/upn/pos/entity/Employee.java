package pe.edu.upn.pos.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(
        name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(name = "employee_ak_1", columnNames = "document_number"),
                @UniqueConstraint(name = "employee_ak_2", columnNames = "phone"),
        },
        indexes = {
                @Index(name = "employee_idx_1", columnList = "gender_id", unique = true),
                @Index(name = "employee_idx_2", columnList = "document_type_id", unique = true),
                @Index(name = "employee_idx_2", columnList = "nationality_id", unique = true),
        }
)
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private UserAccount userAccount;

    @Column(name = "given_names", nullable = false, length = 50)
    private String givenNames;

    @Column(name = "first_last_name", nullable = false, length = 50)
    private String firstLastName;

    @Column(name = "second_last_name", nullable = false, length = 50)
    private String secondLastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nationality_id", nullable = false)
    private Nationality nationality;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

}