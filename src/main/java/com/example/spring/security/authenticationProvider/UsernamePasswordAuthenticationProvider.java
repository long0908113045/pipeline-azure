package com.example.spring.security.authenticationProvider;

import com.example.spring.security.user.service.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsernamePasswordAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    private SecurityUserDetailService securityUserDetailService;

    public UsernamePasswordAuthenticationProvider() {
        setUserDetailsService(securityUserDetailService);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }
}
