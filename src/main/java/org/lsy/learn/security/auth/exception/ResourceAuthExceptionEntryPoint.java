package org.lsy.learn.security.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lsy.learn.security.config.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * springSecurity异常处理，可根据异常的不同而细分处理
 */
@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if (authException instanceof UsernameNotFoundException) {

            writer.write(mapper.writeValueAsString(R.failure(5001, authException.getMessage())));
        }
        if (authException instanceof JwtTokenException) {

            writer.write(mapper.writeValueAsString(R.failure(5002, authException.getMessage())));
        }else if (authException instanceof InsufficientAuthenticationException) {

            writer.write(mapper.writeValueAsString(R.failure(401, authException.getMessage())));
        }
    }
}
