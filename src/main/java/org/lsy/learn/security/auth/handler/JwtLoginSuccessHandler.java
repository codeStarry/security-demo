package org.lsy.learn.security.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lsy.learn.security.auth.service.JwtUserDetails;
import org.lsy.learn.security.config.Constants;
import org.lsy.learn.security.config.R;
import org.lsy.learn.security.tools.JwtUtils;
import org.lsy.learn.security.tools.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户认证成功处理器
 */
@Component
public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        String initToken = JwtUtils.createToken(userDetails.getId().longValue());
        //token加密
        userDetails.setToken(initToken);

        RedisUtils.set(Constants.TOKEN_CACHE_PREFIX + userDetails.getId(), initToken, 60 * 60);
        response.getWriter().write(mapper.writeValueAsString(R.ok(userDetails)));
    }
}
