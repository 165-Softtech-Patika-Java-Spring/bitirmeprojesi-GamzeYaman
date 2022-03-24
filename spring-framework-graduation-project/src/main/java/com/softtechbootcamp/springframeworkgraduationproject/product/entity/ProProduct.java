package com.softtechbootcamp.springframeworkgraduationproject.product.entity;

import com.softtechbootcamp.springframeworkgraduationproject.general.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRO_PRODUCT")
@Getter
@Setter
public class ProProduct extends BaseEntity {

    @Id
    @SequenceGenerator(name = "ProProduct", sequenceName = "PRO_PRODUCT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "ProProduct")
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 50, nullable = false)
    private String productName;

    @Column(name = "BASIC_PRODUCT_PRICE", precision = 19, scale = 2, nullable = false)
    private BigDecimal basicProductPrice;

    @Column(name = "ID_PRODUCT_TYPE", nullable = false)
    private Long productTypeId;

    @Column(name = "TAX_RATE_RESULT", precision = 19, scale = 4, nullable = false)
    private BigDecimal taxRateResult;

    @Column(name = "PRODUCT_PRICE_WITH_TAX", precision = 19, scale = 2, nullable = false)
    private BigDecimal productPriceWithTax;



}

