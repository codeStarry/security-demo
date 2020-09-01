package org.lsy.learn.security.auth.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义token异常，继承{@AuthenticationException}
 */
@Getter
@Setter
public class JwtTokenException extends AuthenticationException {

    private String message;

    public JwtTokenException(String message) {
        super(message);
        this.message = message;
    }
}
