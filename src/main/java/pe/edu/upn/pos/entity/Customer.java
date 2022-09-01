package pe.edu.upn.pos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "given_names", nullable = false, length = 50)
    private String givenNames;

    @Column(name = "first_last_name", nullable = false, length = 50)
    private String firstLastName;

    @Column(name = "second_last_name", length = 50)
    private String secondLastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

}