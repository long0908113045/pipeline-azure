package com.example.spring.security.user.service;

import com.example.spring.security.user.model.SecurityUser;
import com.example.spring.security.user.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    @Autowired
    private SecurityUserRepository securityUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final SecurityUser securityUser = securityUserRepository.findByEmail(username);
        if (securityUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(securityUser.getEmail()).password(securityUser.getPassword()).authorities("User").build();
    }
}
