package pe.edu.upn.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "document_type", uniqueConstraints = @UniqueConstraint(name = "document_type_ak_1", columnNames = "type"))
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "length", nullable = false)
    private Integer length;

}