package com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.service;

import com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.dto.SecurityLoginRequestDto;
import com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.enums.EnumJwtConstant;
import com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.security.JwtTokenGenerator;
import com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.security.JwtUserDetails;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.entity.UsUser;
import com.softtechbootcamp.springframeworkgraduationproject.user.service.UsUserService;
import com.softtechbootcamp.springframeworkgraduationproject.user.service.entityService.UsUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsUserService usUserService;
    private final UsUserEntityService usUserEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    /* User is saved with this method. */
    public UsUserDto register(UsUserSaveDto usUserSaveDto) {
        UsUserDto usUserDto = usUserService.saveUser(usUserSaveDto);
        return usUserDto;
    }

    public String login(SecurityLoginRequestDto securityLoginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityLoginRequestDto.getUsername(), securityLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);
        String bearer = EnumJwtConstant.BEARER.getConstant();
        return bearer + token;
    }


    public Long getCurrentUserId(){
        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserId = null;
        if(jwtUserDetails != null){
            jwtUserId = jwtUserDetails.getId();
        }
        return jwtUserId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if(authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
