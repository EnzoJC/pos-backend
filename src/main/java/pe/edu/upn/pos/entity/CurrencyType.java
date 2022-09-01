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
@Table(name = "currency_type")
public class CurrencyType {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "currency", nullable = false, length = 20)
    private String currency;

}