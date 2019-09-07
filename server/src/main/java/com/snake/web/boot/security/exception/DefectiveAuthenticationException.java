package com.snake.web.boot.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by HP on 2018/10/25.
 */
public class DefectiveAuthenticationException extends AuthenticationException {
    public DefectiveAuthenticationException(String msg) {
        super(msg);
    }
}
