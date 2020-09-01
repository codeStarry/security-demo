package org.lsy.learn.security.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户登录逻辑处理
 */
@Slf4j
@Component
public class JwtUserAuthenticationProvider implements AuthenticationProvider {

    private MessageSourceAccessor message = SpringSecurityMessageSource.getAccessor();
    private UserDetailsChecker checker = new PreAuthUserDetailsChecker();

    @Autowired
    private CustomizeUserDetailsService detailsService;

    /**
     * 登录逻辑处理
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Provider。。。。。。。。。。。。。。。。。。。。。");
        JwtUserAuthenticationToken token = (JwtUserAuthenticationToken) authentication;

        if (Objects.isNull(token.getPassword())) {

            throw new BadCredentialsException(message.getMessage("AbstractUserDetailsAuthenticationProvider.passwordNull",
                    "账号或密码错误！"));
        }
        JwtUserDetails userDetails = (JwtUserDetails) detailsService.loadUserByUsername(token.getUsername());
        if (Objects.isNull(userDetails)) {

            throw new UsernameNotFoundException(message.getMessage("AbstractUserDetailsAuthenticationProvider.accountNotRegister",
                    "该账号未注册"));
        }

        boolean flag = BCrypt.checkpw(token.getPassword(), userDetails.getPassword());
        if (!flag) {

            throw new BadCredentialsException(message.getMessage("AbstractUserDetailsAuthenticationProvider.accountError",
                    "账号或密码错误！"));
        }

        //校验账号
        checker.check(userDetails);

        //包装返回JwtUserAuthenticationToken，后续逻辑交给springSecurity处理
        JwtUserAuthenticationToken authenticationToken = new JwtUserAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(token.getDetails());

        return authenticationToken;
    }

    /**
     * 是JwtUserAuthenticationToken时才执行该Provider
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtUserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
