package com.softtechbootcamp.springframeworkgraduationproject.product.service;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.GeneralErrorMessage;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.InvalidInformationExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.ItemNotFoundExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.product.converter.ProProductMapperConverter;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductUpdateDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.entity.ProProduct;
import com.softtechbootcamp.springframeworkgraduationproject.product.service.entityService.ProProductEntityService;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService.ProProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProProductService {
    private final ProProductEntityService proProductEntityService;
    private final ProProductTypeEntityService proProductTypeEntityService;


    public List<ProProductDto> findAllProducts(){
        List<ProProduct> proProductList = proProductEntityService.findAll();
        List<ProProductDto> proProductDtoList = ProProductMapperConverter.INSTANCE.convertToProProductDtoListFromProProductList(proProductList);
        return proProductDtoList;
    }

    /* If product price is valid, product can be saved. */
    public ProProductDto saveProduct(ProProductSaveDto proProductSaveDto){
        validationOfProductPrice(proProductSaveDto.getBasicProductPrice());
        ProProduct proProduct = ProProductMapperConverter.INSTANCE.convertToProProductFromProProductSaveDto(proProductSaveDto);
        proProduct.setProductPriceWithTax(calculatePriceWithTax(proProductSaveDto.getProductTypeId(), proProductSaveDto.getBasicProductPrice()));
        proProduct.setTaxRateResult(proProduct.getProductPriceWithTax().subtract(proProduct.getBasicProductPrice()));
        proProduct = proProductEntityService.save(proProduct);

        ProProductDto proProductDto = ProProductMapperConverter.INSTANCE.convertToProProductDtoFromProProduct(proProduct);
        return proProductDto;
    }

    /* Getting all products according to product type. */
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

    public List<ProProductDto> findAllProductsBetweenTwoPrices(BigDecimal firstPrice, BigDecimal secondPrice){
        validationOfTwoPrices(firstPrice, secondPrice);
        List<ProProduct> proProductList = proProductEntityService.findAllByProductBetweenTwoPrices(firstPrice, secondPrice);
        List<ProProductDto> proProductDtoList = ProProductMapperConverter.INSTANCE.convertToProProductDtoListFromProProductList(proProductList);
        return proProductDtoList;
    }

    public ProProductDto updateProduct(ProProductUpdateDto proProductUpdateDto){
        Long id = proProductUpdateDto.getId();
        Boolean isProductExist = proProductEntityService.existById(id);

        ProProduct proProduct;
        if(isProductExist){
            proProduct = ProProductMapperConverter.INSTANCE.convertToProProductFromProProductUpdateDto(proProductUpdateDto);
            updateCurrentProductPriceWithTax(proProduct, proProductUpdateDto.getBasicProductPrice());
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
        updateCurrentProductPriceWithTax(proProduct, productPrice);
        proProduct = proProductEntityService.save(proProduct);

        ProProductDto proProductDto = ProProductMapperConverter.INSTANCE.convertToProProductDtoFromProProduct(proProduct);
        return proProductDto;
    }

    public void deleteProduct(Long id){
        ProProduct proProduct = proProductEntityService.getIdWithControl(id);
        proProductEntityService.delete(proProduct);
    }

    private void updateCurrentProductPriceWithTax(ProProduct proProduct, BigDecimal price){
        BigDecimal newPrice = calculatePriceWithTax(proProduct.getProductTypeId(), price);
        proProduct.setProductPriceWithTax(newPrice);
        proProduct.setTaxRateResult(proProduct.getProductPriceWithTax().subtract(proProduct.getBasicProductPrice()));
        proProduct = proProductEntityService.save(proProduct);
    }

    private BigDecimal calculatePriceWithTax(Long id, BigDecimal price){

        Long productTypeId = id;
        BigDecimal percentage = BigDecimal.valueOf(100);

        ProProductType proProductType = proProductTypeEntityService.getIdWithControl(productTypeId);

        BigDecimal tax = proProductType.getTaxRate();
        BigDecimal productPrice = price;

        BigDecimal taxCalculation = productPrice.multiply(tax.divide(percentage));
        BigDecimal newProductPriceWithTax = productPrice.add(taxCalculation);

        return newProductPriceWithTax;
    }

    /* Validation process about price cannot be negative. */
    public boolean validationOfProductPrice(BigDecimal productPrice) {
        if(productPrice.compareTo(BigDecimal.ZERO) > 0){
            return true;
        }else{
            throw new InvalidInformationExceptions(GeneralErrorMessage.PRICE_NOT_BE_NEGATIVE);
        }
    }

    /* First price must be litter than second one. */
    private boolean validationOfTwoPrices(BigDecimal firstPrice, BigDecimal secondPrice){
        if(firstPrice.compareTo(secondPrice) < secondPrice.compareTo(firstPrice)){
            return true;
        }else{
           throw new InvalidInformationExceptions(GeneralErrorMessage.COMPARE_TWO_PRICES);
        }
    }
}
