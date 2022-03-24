package com.softtechbootcamp.springframeworkgraduationproject.productType.service;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.GeneralErrorMessage;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.DuplicateException;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.InvalidInformationExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.product.service.ProProductService;
import com.softtechbootcamp.springframeworkgraduationproject.productType.converter.ProProductTypeMapperConverter;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductDetailsDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService.ProProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProProductTypeService {
    private final ProProductTypeEntityService proProductTypeEntityService;
    private final ProProductService proProductService;

    /* Product type is saved with type name and tax rate. Product type cannot be saved with same type name, so it has to be unique.*/
    public ProProductTypeDto saveProductType(ProProductTypeSaveDto proProductTypeSaveDto){
        isProductTypeNameExist(proProductTypeSaveDto.getProductTypeName());
        validationOfTaxRate(proProductTypeSaveDto.getTaxRate());
        ProProductType proProductType = ProProductTypeMapperConverter.INSTANCE.convertToProProductTypeFromProProductTypeSaveDto(proProductTypeSaveDto);
        proProductType.setProductTypeName(proProductTypeSaveDto.getProductTypeName().toUpperCase(Locale.ROOT));
        proProductType.setTaxRate(proProductTypeSaveDto.getTaxRate());
        proProductType = proProductTypeEntityService.save(proProductType);

        ProProductTypeDto proProductTypeDto = ProProductTypeMapperConverter.INSTANCE.convertToProProductTypeDtoFromProProductType(proProductType);
        return proProductTypeDto;
    }

    /* Updating product type tax rate by using unique product type name. */
    public ProProductTypeDto updateTaxRateOfProductType(String productTypeName, BigDecimal taxRate){
        validationOfTaxRate(taxRate);
        ProProductType proProductType = proProductTypeEntityService.update(productTypeName, taxRate);
        ProProductTypeDto proProductTypeDto = ProProductTypeMapperConverter.INSTANCE.convertToProProductTypeDtoFromProProductType(proProductType);
        return proProductTypeDto;
    }

    /* Getting some summarized information about product for each productType. */
    public List<ProProductDetailsDto> getProductTypeDetails(){
        List<ProProductDetailsDto> proProductDetailsDto = proProductTypeEntityService.getProductTypeDetails();
        return proProductDetailsDto;
    }

    /* Validation process about product type name is used or not. Name was converted to uppercase because of saving style of product type name to database. */
    public Boolean isProductTypeNameExist(String name){
        name = name.toUpperCase(Locale.ROOT);
        Boolean isExist = proProductTypeEntityService.isProductTypeNameExist(name);
        if(!isExist){
            return true;
        }else{
            throw new DuplicateException(GeneralErrorMessage.PRODUCT_TYPE_ERROR);
        }
    }

    /* Validation process about tax rate is negative or not. */
    public Boolean validationOfTaxRate(BigDecimal taxRate) {
        if(taxRate.compareTo(BigDecimal.ZERO) > 0){
            return true;
        }else{
            throw new InvalidInformationExceptions(GeneralErrorMessage.TAX_RATE_NOT_BE_NEGATIVE);
        }
    }
}
