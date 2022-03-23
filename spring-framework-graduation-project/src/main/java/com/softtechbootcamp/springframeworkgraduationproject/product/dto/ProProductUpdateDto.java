package com.softtechbootcamp.springframeworkgraduationproject.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProProductUpdateDto {
    private Long id;
    private String productName;
    private BigDecimal basicProductPrice;
    private Long productTypeId;
}
