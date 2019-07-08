package ru.ankoks.jwtdemo.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * User: ankoks
 * Date: 05/07/2019
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
