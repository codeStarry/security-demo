package org.lsy.learn.security.auth.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.security.auth.exception.ResourceAuthExceptionEntryPoint;
import org.lsy.learn.security.auth.handler.JwtLoginFailureHandler;
import org.lsy.learn.security.auth.handler.JwtLoginSuccessHandler;
import org.lsy.learn.security.auth.service.JwtUserAuthenticationToken;
import org.lsy.learn.security.model.User;
import org.lsy.learn.security.tools.MultiReadHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录过滤器
 */
@Slf4j
@Component
public class JwtUserAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ResourceAuthExceptionEntryPoint entryPoint;

    public JwtUserAuthenticationFilter(AuthenticationManager manager,
                                       JwtLoginSuccessHandler successHandler,
                                       JwtLoginFailureHandler failureHandler) {

        //定义登录路径和请求方式
        super(new AntPathRequestMatcher("/login", "POST"));
        //设置AuthenticationManager
        this.setAuthenticationManager(manager);
        //登录成功处理器
        this.setAuthenticationSuccessHandler(successHandler);
        //登录失败处理器
        this.setAuthenticationFailureHandler(failureHandler);
    }

    /**
     * 包装JwtUserAuthenticationToken；尝试进行身份验证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        log.info("userFilter。。。。。。。。。。。。。。。。");

        MultiReadHttpServletRequest servletRequest = new MultiReadHttpServletRequest(request);

        User user = JSONObject.parseObject(servletRequest.getInputStream(), User.class);

        JwtUserAuthenticationToken authenticationToken = new JwtUserAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationToken.setDetails(authenticationDetailsSource.buildDetails(servletRequest));

        Authentication authentication = null;

        try {

            authentication = this.getAuthenticationManager().authenticate(authenticationToken);

        }catch (AuthenticationException ex) {
            ex.printStackTrace();
            entryPoint.commence(servletRequest, response, new UsernameNotFoundException(ex.getMessage()));
        }

        return authentication;
    }
}
