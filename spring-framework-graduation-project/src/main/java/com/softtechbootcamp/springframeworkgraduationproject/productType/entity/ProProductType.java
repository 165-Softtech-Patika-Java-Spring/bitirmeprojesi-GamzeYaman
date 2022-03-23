package com.softtechbootcamp.springframeworkgraduationproject.productType.entity;

import com.softtechbootcamp.springframeworkgraduationproject.general.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRO_PRODUCT_TYPE")
@Getter
@Setter
public class ProProductType extends BaseEntity {

    @Id
    @SequenceGenerator(name = "ProProductType", sequenceName = "PRO_PRODUCT_TYPE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "ProProductType")
    private Long id;

    @Column(name = "PRODUCT_TYPE_NAME", length = 30, nullable = false)
    private String productTypeName;

    @Column(name = "TAX_RATE", precision = 19, scale = 2, nullable = false)
    private BigDecimal taxRate;
}
