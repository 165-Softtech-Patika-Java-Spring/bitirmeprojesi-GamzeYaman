package com.softtechbootcamp.springframeworkgraduationproject.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsUserSaveDto {
    private String name;
    private String surname;
    private String username;
    private String userPassword;
}
