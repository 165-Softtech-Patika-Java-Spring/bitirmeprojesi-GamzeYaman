package com.softtechbootcamp.springframeworkgraduationproject.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
public class ProProductSaveDto {
    private String productName;
    private BigDecimal basicProductPrice;
    private Long productTypeId;


}
