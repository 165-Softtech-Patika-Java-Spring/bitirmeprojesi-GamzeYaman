package com.softtechbootcamp.springframeworkgraduationproject.user.service;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.GeneralErrorMessage;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.DuplicateException;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.ItemNotFoundExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.user.converter.UsUserMapperConverter;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.entity.UsUser;
import com.softtechbootcamp.springframeworkgraduationproject.user.service.entityService.UsUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsUserService {
    private final UsUserEntityService usUserEntityService;
    private final PasswordEncoder passwordEncoder;

    /* User is saved with name, surname, username and password. User cannot be saved with same username, so it has to be unique.*/
    public UsUserDto saveUser(UsUserSaveDto usUserSaveDto){
        isUsernameExist(usUserSaveDto.getUsername());
        UsUser usUser = UsUserMapperConverter.INSTANCE.convertToUsUserFromUsUserSaveDto(usUserSaveDto);
        String encodedPassword = passwordEncoder.encode(usUserSaveDto.getUserPassword());
        usUser.setUserPassword(encodedPassword);
        usUser = usUserEntityService.save(usUser);

        UsUserDto usUserDto = UsUserMapperConverter.INSTANCE.convertToUsUserDtoFromUsUser(usUser);
        return usUserDto;
    }

    public UsUserDto updateUser(UsUserDto usUserDto){
        Long id  = usUserDto.getId();
        Boolean isUserExist = usUserEntityService.existById(id);

        UsUser usUser;
        if(isUserExist){
            usUser = UsUserMapperConverter.INSTANCE.convertToUsUserFromUsUserDto(usUserDto);
            usUser = usUserEntityService.save(usUser);
        }else{
            throw new ItemNotFoundExceptions(GeneralErrorMessage.USER_NOT_FOUND);
        }

        UsUserDto usUserDtoUpdate = UsUserMapperConverter.INSTANCE.convertToUsUserDtoFromUsUser(usUser);
        return usUserDtoUpdate;
    }

    /* Validation about username is taken or not? */
    private boolean isUsernameExist(String username){
        Boolean isExist = usUserEntityService.isUsernameExist(username);
        if(!isExist){
            return true;
        }else{
            throw new DuplicateException(GeneralErrorMessage.USERNAME_ERROR);
        }
    }

    public void deleteUser(Long id){
        UsUser usUser = usUserEntityService.getIdWithControl(id);
        usUserEntityService.delete(usUser);
    }

}
