package com.softtechbootcamp.springframeworkgraduationproject.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsUserDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String userPassword;
}
