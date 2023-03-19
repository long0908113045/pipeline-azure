package com.example.spring.security.authentication;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    public AuthenticationToken(String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }

    public AuthenticationToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public String getToken() {
        return token;
    }

    public Object getPrincipal() {
        return token;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        token = null;
    }
}
