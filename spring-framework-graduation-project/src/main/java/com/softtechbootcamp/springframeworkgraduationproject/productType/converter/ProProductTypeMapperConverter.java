package com.softtechbootcamp.springframeworkgraduationproject.productType.converter;

import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProProductTypeMapperConverter {

    ProProductTypeMapperConverter INSTANCE = Mappers.getMapper(ProProductTypeMapperConverter.class);
    ProProductType convertToProProductTypeFromProProductTypeSaveDto(ProProductTypeSaveDto proProductTypeSaveDto);
    ProProductTypeDto convertToProProductTypeDtoFromProProductType(ProProductType proProductType);
}
