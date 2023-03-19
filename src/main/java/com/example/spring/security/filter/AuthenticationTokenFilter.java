package com.example.spring.security.filter;

import com.example.spring.security.authentication.AuthenticationToken;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;

import java.io.IOException;

public class AuthenticationTokenFilter implements Filter {
    private AuthenticationManager authenticationManager;

    public AuthenticationTokenFilter() {
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String Authorization = "Authorization";
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Authorization);
        if (token.startsWith("Bearer ")) {
            AuthenticationToken authenticationToken = new AuthenticationToken(token);
            authenticationToken.setDetails(null);
            this.authenticationManager.authenticate(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
