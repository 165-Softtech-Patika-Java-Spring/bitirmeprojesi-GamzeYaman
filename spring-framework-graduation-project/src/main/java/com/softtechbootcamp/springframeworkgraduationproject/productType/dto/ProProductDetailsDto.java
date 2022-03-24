package com.softtechbootcamp.springframeworkgraduationproject.productType.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ProProductDetailsDto {
    private final String productTypeName;
    private final BigDecimal taxRate;
    private final BigDecimal minProductPrice;
    private final BigDecimal maxProductPrice;
    private final Long numberOfProduct;
}
