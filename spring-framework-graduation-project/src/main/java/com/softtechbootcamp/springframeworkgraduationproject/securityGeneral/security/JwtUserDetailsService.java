package com.softtechbootcamp.springframeworkgraduationproject.securityGeneral.security;

import com.softtechbootcamp.springframeworkgraduationproject.user.entity.UsUser;
import com.softtechbootcamp.springframeworkgraduationproject.user.service.entityService.UsUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private  final UsUserEntityService usUserEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UsUser usUser = usUserEntityService.findByUsername(username);
       return JwtUserDetails.create(usUser);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        UsUser usUser = usUserEntityService.getIdWithControl(id);
        return JwtUserDetails.create(usUser);
    }
}
