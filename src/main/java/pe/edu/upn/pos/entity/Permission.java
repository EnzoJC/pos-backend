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
@Table(name = "permission")
public class Permission {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "permission", nullable = false, length = 50)
    private String permission;

}