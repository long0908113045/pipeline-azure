package com.example.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CommonAuthenticationException extends AuthenticationException {
    public CommonAuthenticationException(String msg) {
        super(msg);
    }
}
