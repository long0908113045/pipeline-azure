package com.example.spring.security;

import com.example.spring.security.user.service.SecurityUserDetailService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityApplicationConfiguration {

  @Resource
  private SecurityUserDetailService securityUserDetailService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .anyRequest()
      .authenticated()
      .and()
      .httpBasic();
    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Autowired
  void registerProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
    authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
  }

  public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(securityUserDetailService);
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return daoAuthenticationProvider;
  }
}
