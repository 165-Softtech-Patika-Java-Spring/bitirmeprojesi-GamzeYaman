package com.softtechbootcamp.springframeworkgraduationproject.product.entity;

import com.softtechbootcamp.springframeworkgraduationproject.general.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PROPRODUCT")
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

    @Column(name = "ID_PRODUCT_TYPE")
    private Long productTypeId;

    @Column(name = "TAX_RATE", precision = 19, scale = 4)
    private BigDecimal taxRate;

    @Column(name = "PRODUCT_PRICE_WITH_TAX", precision = 19, scale = 2)
    private BigDecimal productPriceWithTax;



}

