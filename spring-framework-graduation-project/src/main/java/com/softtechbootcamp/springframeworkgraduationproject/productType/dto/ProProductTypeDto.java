package com.softtechbootcamp.springframeworkgraduationproject.productType.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProProductTypeDto {
    private Long id;
    private String productTypeName;
    private BigDecimal taxRate;
}
