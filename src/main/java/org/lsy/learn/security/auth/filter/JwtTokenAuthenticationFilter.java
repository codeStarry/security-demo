package org.lsy.learn.security.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.security.auth.exception.JwtTokenException;
import org.lsy.learn.security.auth.exception.ResourceAuthExceptionEntryPoint;
import org.lsy.learn.security.auth.service.CustomizeUserDetailsService;
import org.lsy.learn.security.auth.service.JwtUserAuthenticationToken;
import org.lsy.learn.security.auth.service.JwtUserDetails;
import org.lsy.learn.security.config.Constants;
import org.lsy.learn.security.tools.JwtUtils;
import org.lsy.learn.security.tools.MultiReadHttpServletRequest;
import org.lsy.learn.security.tools.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwtToken过滤器
 */
@Slf4j
@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private ResourceAuthExceptionEntryPoint entryPoint;

    @Autowired
    private CustomizeUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("TokenFilter。。。。。。。。。。。。。。。。");
        MultiReadHttpServletRequest servletRequest = new MultiReadHttpServletRequest(request);
        String token = servletRequest.getHeader(AUTHORIZATION);

        /**
         * 如果该请求未携带token，就进入下一个过滤器，
         */
        if (null == token) {
            filterChain.doFilter(servletRequest, response);
            return;
        }

        //token校验逻辑处理
        try {
            Integer userId = JwtUtils.getUserId(token);
            String serverToken = RedisUtils.get(Constants.TOKEN_CACHE_PREFIX + userId);

            if (null == serverToken || "".equals(serverToken)) {

                entryPoint.commence(servletRequest, response, new JwtTokenException("token失效，请重新登录!"));
                return;
            }
            if (!Objects.equals(token, serverToken)) {

                entryPoint.commence(servletRequest, response, new JwtTokenException("token被恶意程序篡改，请注意网络安全!"));
                return;
            }

            JwtUserDetails details = userDetailsService.findById(userId);
            JwtUserAuthenticationToken authenticationToken = new JwtUserAuthenticationToken(details, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (Exception exception) {
            exception.printStackTrace();
            entryPoint.commence(servletRequest, response, new JwtTokenException("token被恶意程序篡改，请注意网络安全!"));
            return;
        }

        filterChain.doFilter(servletRequest, response);
    }
}
