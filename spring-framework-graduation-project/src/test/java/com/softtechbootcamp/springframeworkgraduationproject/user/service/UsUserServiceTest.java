package com.softtechbootcamp.springframeworkgraduationproject.user.service;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.ItemNotFoundExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.entity.UsUser;
import com.softtechbootcamp.springframeworkgraduationproject.user.service.entityService.UsUserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsUserServiceTest {

    @InjectMocks
    private UsUserService usUserService;

    @Mock
    private UsUserEntityService usUserEntityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldSaveUser() {
        String username = "TestUser2";
        Long id = 3L;
        UsUserSaveDto usUserSaveDto = mock(UsUserSaveDto.class);
        when(usUserSaveDto.getUsername()).thenReturn(username);
        when(usUserSaveDto.getUserPassword()).thenReturn("12345");

        UsUser usUser = mock(UsUser.class);
        when(usUser.getId()).thenReturn(id);
        when(passwordEncoder.encode(anyString())).thenReturn("bcd34yI");

        when(usUserEntityService.save(any())).thenReturn(usUser);

        UsUserDto result = usUserService.saveUser(usUserSaveDto);

        assertEquals(id, result.getId());
    }

    @Test
    void shouldUpdateUser() {
        Long id = 1L;

        UsUserDto usUserDto = mock(UsUserDto.class);
        UsUser usUser = mock(UsUser.class);
        when(usUser.getId()).thenReturn(id);

        boolean isExist = true;

        when(usUserEntityService.existById(any())).thenReturn(isExist);
        when(usUserEntityService.save(any())).thenReturn(usUser);


        UsUserDto result = usUserService.updateUser(usUserDto);

        assertEquals(id, result.getId());

        verify(usUserEntityService).existById(anyLong());
    }

    @Test
    void shouldNotUpdateUserDoesNotExist(){
        UsUserDto usUserDto = mock(UsUserDto.class);
        when(usUserEntityService.existById(anyLong())).thenThrow(ItemNotFoundExceptions.class);

        ItemNotFoundExceptions itemNotFoundExceptions = assertThrows(ItemNotFoundExceptions.class, ()-> usUserService.updateUser(usUserDto));

        verify(usUserEntityService).existById(anyLong());
    }

    @Test
    void shouldDeleteUser(){
        UsUser usUser = mock(UsUser.class);
        when(usUserEntityService.getIdWithControl(anyLong())).thenReturn(usUser);

        usUserService.deleteUser(anyLong());

        verify(usUserEntityService).getIdWithControl(anyLong());
        verify(usUserEntityService).delete(any());
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() {

        when(usUserEntityService.getIdWithControl(anyLong())).thenThrow(ItemNotFoundExceptions.class);

        assertThrows(ItemNotFoundExceptions.class, () -> usUserService.deleteUser(anyLong()));

        verify(usUserEntityService).getIdWithControl(anyLong());
    }
}