package com.softtechbootcamp.springframeworkgraduationproject.product.service;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.GeneralErrorMessage;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.BusinessExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.InvalidInformationExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.ItemNotFoundExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.product.converter.ProProductMapperConverter;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.entity.ProProduct;
import com.softtechbootcamp.springframeworkgraduationproject.product.service.entityService.ProProductEntityService;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService.ProProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProProductService {
    private final ProProductEntityService proProductEntityService;
    private final ProProductTypeEntityService proProductTypeEntityService;


    public List<ProProductDto> findAllProducts(){
        List<ProProduct> proProductList = proProductEntityService.findAll();
        List<ProProductDto> proProductDtoList = ProProductMapperConverter.INSTANCE.convertToProProductDtoListFromProProductList(proProductList);
        return proProductDtoList;
    }

    public ProProductDto saveProduct(ProProductSaveDto proProductSaveDto){
        ProProduct proProduct = ProProductMapperConverter.INSTANCE.convertToProProductFromProProductSaveDto(proProductSaveDto);
        validationOfProductPrice(proProductSaveDto.getBasicProductPrice());
        proProduct.setProductPriceWithTax(calculatePriceWithKdvTax(proProductSaveDto));
        proProduct.setTaxRate(proProduct.getProductPriceWithTax().subtract(proProduct.getBasicProductPrice()));
        proProduct = proProductEntityService.save(proProduct);

        ProProductDto proProductDto = ProProductMapperConverter.INSTANCE.convertToProProductDtoFromProProduct(proProduct);
        return proProductDto;
    }

    public BigDecimal calculatePriceWithKdvTax(ProProductSaveDto proProductSaveDto){

        Long productTypeId = proProductSaveDto.getProductTypeId();
        BigDecimal percentage = BigDecimal.valueOf(100);

        ProProductType proProductType = proProductTypeEntityService.getIdWithControl(productTypeId);

        BigDecimal kdvTax = proProductType.getTaxRate();
        BigDecimal productPrice = proProductSaveDto.getBasicProductPrice();

        BigDecimal taxCalculation = productPrice.multiply((kdvTax.divide(percentage)));
        BigDecimal newProductPrice = productPrice.add(taxCalculation);

        return newProductPrice;
    }

    public List<ProProductDto> findByProductTypeId(Long id){
        List<ProProduct> proProductList = proProductEntityService.findByProductTypeId(id);

        List<ProProductDto> proProductDtoList;
        if(!proProductList.isEmpty()){
            proProductDtoList = ProProductMapperConverter.INSTANCE.convertToProProductDtoListFromProProductList(proProductList);
        }else{
            throw new ItemNotFoundExceptions(GeneralErrorMessage.PRODUCT_TYPE_NOT_FOUND);
        }
        return proProductDtoList;
    }


    public ProProductDto updateProduct(ProProductDto proProductDto){
        Long id = proProductDto.getId();
        Boolean isProductExist = proProductEntityService.existById(id);

        ProProduct proProduct;
        if(isProductExist){
            proProduct = ProProductMapperConverter.INSTANCE.convertToProProductFromProProductDto(proProductDto);
            proProduct = proProductEntityService.save(proProduct);
        }else{
            throw new ItemNotFoundExceptions(GeneralErrorMessage.PRODUCT_NOT_FOUND);
        }

        ProProductDto proProductDtoUpdate = ProProductMapperConverter.INSTANCE.convertToProProductDtoFromProProduct(proProduct);
        return proProductDtoUpdate;
    }

    public ProProductDto updateProductPrice(Long id, BigDecimal productPrice){
        validationOfProductPrice(productPrice);
        ProProduct proProduct = proProductEntityService.getIdWithControl(id);
        proProduct.setBasicProductPrice(productPrice);
        proProduct = proProductEntityService.save(proProduct);

        ProProductDto proProductDto = ProProductMapperConverter.INSTANCE.convertToProProductDtoFromProProduct(proProduct);
        return proProductDto;
    }

    public void deleteProduct(Long id){
        ProProduct proProduct = proProductEntityService.getIdWithControl(id);
        proProductEntityService.delete(proProduct);
    }

    //TODO:rename method
    public List<ProProductDto> findAllProducts(BigDecimal firstPrice, BigDecimal secondPrice){
       List<ProProduct> proProductList = proProductEntityService.findAllByProductPricesBetween(firstPrice, secondPrice);
       List<ProProductDto> proProductDtoList = ProProductMapperConverter.INSTANCE.convertToProProductDtoListFromProProductList(proProductList);
       return proProductDtoList;
    }

    public boolean validationOfProductPrice(BigDecimal productPrice) {
        if(productPrice.compareTo(BigDecimal.ZERO) > 0){
            return true;
        }else{
            throw new InvalidInformationExceptions(GeneralErrorMessage.PRICE_NOT_BE_NEGATIVE);
        }
    }

}
