package org.lsy.learn.security.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomizeException extends RuntimeException{

    private Integer code;

    private String message;

    public CustomizeException() {}

    public CustomizeException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomizeException(String message) {
        super(message);
        this.message = message;
    }
}
