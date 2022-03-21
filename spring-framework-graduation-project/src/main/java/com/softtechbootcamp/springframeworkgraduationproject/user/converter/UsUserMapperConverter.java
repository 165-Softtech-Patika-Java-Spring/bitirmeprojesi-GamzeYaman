package com.softtechbootcamp.springframeworkgraduationproject.user.converter;

import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.entity.UsUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsUserMapperConverter {

    UsUserMapperConverter INSTANCE = Mappers.getMapper(UsUserMapperConverter.class);
    UsUser convertToUsUserFromUsUserSaveDto(UsUserSaveDto usUserSaveDto);
    UsUserDto convertToUsUserDtoFromUsUser(UsUser usUser);
    UsUser convertToUsUserFromUsUserDto(UsUserDto usUserDto);
}

