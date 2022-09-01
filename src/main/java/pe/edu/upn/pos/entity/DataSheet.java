package pe.edu.upn.pos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "data_sheet")
public class DataSheet {
    @EmbeddedId
    private DataSheetId id;

    @MapsId("typeDetailId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_detail_id", nullable = false)
    private AttributeDetail typeDetail;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "detail", nullable = false, length = 100)
    private String detail;

}