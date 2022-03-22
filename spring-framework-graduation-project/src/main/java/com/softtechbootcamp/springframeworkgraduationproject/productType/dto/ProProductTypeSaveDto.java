package com.softtechbootcamp.springframeworkgraduationproject.productType.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProProductTypeSaveDto {
    private String productTypeName;
    private BigDecimal taxRate;
}
