package com.example.spring.security.user.service;

import com.example.spring.security.user.model.AppUser;
import com.example.spring.security.user.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AppUser appUser = appUserRepository.findByEmail(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(appUser.getEmail()).password(appUser.getPassword()).authorities("User").build();
    }
}
