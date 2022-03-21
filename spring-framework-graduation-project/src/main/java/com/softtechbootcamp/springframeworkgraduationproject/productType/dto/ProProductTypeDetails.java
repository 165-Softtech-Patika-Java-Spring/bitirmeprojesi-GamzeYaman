package com.softtechbootcamp.springframeworkgraduationproject.productType.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProProductTypeDetails {
    private String productTypeName;
    private BigDecimal taxRate;
    private BigDecimal minProductPriceWithTax;
    private BigDecimal maxProductPriceWithTax;
    private BigDecimal avgProductPriceWithTax;
    private Long numberOfProducts;
}
