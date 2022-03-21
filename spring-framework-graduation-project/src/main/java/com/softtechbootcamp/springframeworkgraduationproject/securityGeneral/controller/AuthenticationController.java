package com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.controller;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.dto.SecurityLoginRequestDto;
import com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.service.AuthenticationService;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecurityLoginRequestDto securityLoginRequestDto){
       String token = authenticationService.login(securityLoginRequestDto);
       return  ResponseEntity.ok(RestResponse.of(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsUserSaveDto usUserSaveDto){
        UsUserDto usUserDto = authenticationService.register(usUserSaveDto);
        return ResponseEntity.ok(RestResponse.of(usUserDto));
    }
}
