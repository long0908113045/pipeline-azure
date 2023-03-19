package com.example.spring.security;

import com.example.spring.security.authenticationEntryPoint.ExceptionAuthenticationEntryPoint;
import com.example.spring.security.authenticationProvider.TokenAuthenticationProvider;
import com.example.spring.security.filter.AuthenticationTokenFilter;
import com.example.spring.security.user.service.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityApplicationConfiguration {
//    @Autowired
    private ExceptionAuthenticationEntryPoint exceptionAuthenticationEntryPoint;

//    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

//    @Autowired
    private AppUserDetailService appUserDetailService;

//    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    public SecurityApplicationConfiguration(AuthenticationConfiguration authenticationConfiguration,
                                            ExceptionAuthenticationEntryPoint exceptionAuthenticationEntryPoint,
                                            AppUserDetailService appUserDetailService,
                                            TokenAuthenticationProvider tokenAuthenticationProvider){
        this.authenticationConfiguration = authenticationConfiguration;
        this.exceptionAuthenticationEntryPoint = exceptionAuthenticationEntryPoint;
        this.appUserDetailService = appUserDetailService;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest("/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling().authenticationEntryPoint(exceptionAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.httpBasic();
        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        authenticationManagerBuilder.authenticationProvider(jsonWebTokenAuthenticationProvider());
    }

    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    public AuthenticationProvider jsonWebTokenAuthenticationProvider() {
        return tokenAuthenticationProvider;
    }

    public AuthenticationTokenFilter authenticationTokenFilter() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return authenticationTokenFilter;
    }
}
