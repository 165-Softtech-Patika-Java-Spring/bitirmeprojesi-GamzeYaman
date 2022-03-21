package com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityLoginRequestDto {
    private String username;
    private String password;
}
