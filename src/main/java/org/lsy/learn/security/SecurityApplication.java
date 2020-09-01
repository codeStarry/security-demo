package org.lsy.learn.security;

import org.lsy.learn.security.config.CustomizeException;
import org.lsy.learn.security.config.R;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @ExceptionHandler(CustomizeException.class)
    public R<String> exceptionHandler(CustomizeException ex) {
        return R.failure(ex.getMessage());
    }
}
