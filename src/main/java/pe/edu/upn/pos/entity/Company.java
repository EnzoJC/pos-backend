package pe.edu.upn.pos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "ruc", nullable = false, length = 20)
    private String ruc;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone_1", nullable = false, length = 50)
    private String phone1;

    @Column(name = "phone_2", length = 50)
    private String phone2;

    @Column(name = "phone_3", length = 50)
    private String phone3;

    @Column(name = "url", length = 100)
    private String url;

    @Column(name = "image", nullable = false, length = 250)
    private String image;

}