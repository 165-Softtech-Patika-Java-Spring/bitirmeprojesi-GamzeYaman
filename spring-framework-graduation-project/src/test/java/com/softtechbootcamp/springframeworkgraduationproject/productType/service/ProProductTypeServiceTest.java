package com.softtechbootcamp.springframeworkgraduationproject.productType.service;

import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService.ProProductTypeEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProProductTypeServiceTest {

    @InjectMocks
    private ProProductTypeService proProductTypeService;

    @Mock
    private ProProductTypeEntityService proProductTypeEntityService;

    @Test
    void shouldSaveProductType() {
        ProProductTypeSaveDto proProductTypeSaveDto = mock(ProProductTypeSaveDto.class);
        when(proProductTypeSaveDto.getProductTypeName()).thenReturn("Test3");
        when(proProductTypeSaveDto.getTaxRate()).thenReturn(BigDecimal.valueOf(15));

        ProProductType proProductType = mock(ProProductType.class);
        when(proProductType.getId()).thenReturn(5L);

        when(proProductTypeEntityService.save(any())).thenReturn(proProductType);

        ProProductTypeDto result = proProductTypeService.saveProductType(proProductTypeSaveDto);

        assertEquals(5, result.getId());
    }

    @Test
    void shouldUpdateTaxRateOfProductType() {
        String productTypeName =  "Food";
        BigDecimal taxRate = BigDecimal.valueOf(6);

        ProProductType proProductType = mock(ProProductType.class);
        when(proProductType.getProductTypeName()).thenReturn(productTypeName);
        when(proProductType.getTaxRate()).thenReturn(taxRate);
        when(proProductTypeEntityService.update(productTypeName, taxRate)).thenReturn(proProductType);

        ProProductTypeDto result = proProductTypeService.updateTaxRateOfProductType(proProductType.getProductTypeName(), proProductType.getTaxRate());

        assertEquals(BigDecimal.valueOf(6), result.getTaxRate());

    }

}