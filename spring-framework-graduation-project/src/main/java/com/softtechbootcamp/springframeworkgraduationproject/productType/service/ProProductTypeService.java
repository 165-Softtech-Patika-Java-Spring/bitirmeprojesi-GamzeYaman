package com.softtechbootcamp.springframeworkgraduationproject.productType.service;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.GeneralErrorMessage;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.DuplicateException;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.InvalidInformationExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.productType.converter.ProProductTypeMapperConverter;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService.ProProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProProductTypeService {
    private final ProProductTypeEntityService proProductTypeEntityService;

    public ProProductTypeDto saveProductType(ProProductTypeSaveDto proProductTypeSaveDto){
        isProductTypeNameExist(proProductTypeSaveDto.getProductTypeName());
        validationOfTaxRate(proProductTypeSaveDto.getTaxRate());
        ProProductType proProductType = ProProductTypeMapperConverter.INSTANCE.convertToProProductTypeFromProProductTypeSaveDto(proProductTypeSaveDto);
        proProductType = proProductTypeEntityService.save(proProductType);

        ProProductTypeDto proProductTypeDto = ProProductTypeMapperConverter.INSTANCE.convertToProProductTypeDtoFromProProductType(proProductType);
        return proProductTypeDto;
    }

    public ProProductTypeDto updateKdvRateOfProductType(String productTypeName, BigDecimal taxRate){
        validationOfTaxRate(taxRate);
        ProProductType proProductType = proProductTypeEntityService.update(productTypeName, taxRate);
        ProProductTypeDto proProductTypeDto = ProProductTypeMapperConverter.INSTANCE.convertToProProductTypeDtoFromProProductType(proProductType);
        return proProductTypeDto;
    }

    public List<Object> getProductTypeDetails(){
        List<Object> proProductDetails = proProductTypeEntityService.getProductTypeDetails();
        return proProductDetails;
    }

    public Boolean isProductTypeNameExist(String name){
        Boolean isExist = proProductTypeEntityService.isProductTypeExist(name);
        if(!isExist){
            return true;
        }else{
            throw new DuplicateException(GeneralErrorMessage.PRODUCT_TYPE_ERROR);
        }
    }

    private boolean validationOfTaxRate(BigDecimal taxRate) {
        if(taxRate.compareTo(BigDecimal.ZERO) > 0){
            return true;
        }else{
            throw new InvalidInformationExceptions(GeneralErrorMessage.TAX_RATE_NOT_BE_NEGATIVE);
        }
    }
}
