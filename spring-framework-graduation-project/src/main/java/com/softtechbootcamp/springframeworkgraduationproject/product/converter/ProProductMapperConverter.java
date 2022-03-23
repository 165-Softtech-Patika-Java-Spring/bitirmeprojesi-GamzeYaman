package com.softtechbootcamp.springframeworkgraduationproject.product.converter;

import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductUpdateDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.entity.ProProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProProductMapperConverter {

    ProProductMapperConverter INSTANCE = Mappers.getMapper(ProProductMapperConverter.class);
    ProProduct convertToProProductFromProProductSaveDto(ProProductSaveDto proProductSaveDto);
    ProProduct convertToProProductFromProProductDto(ProProductDto proProductDto);
    ProProductDto convertToProProductDtoFromProProduct(ProProduct proProduct);
    List<ProProductDto> convertToProProductDtoListFromProProductList(List<ProProduct> proProduct);
    ProProduct convertToProProductFromProProductUpdateDto(ProProductUpdateDto proProductUpdateDto);
}
