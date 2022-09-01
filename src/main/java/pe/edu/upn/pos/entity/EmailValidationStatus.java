package pe.edu.upn.pos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "email_validation_status")
public class EmailValidationStatus {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

}