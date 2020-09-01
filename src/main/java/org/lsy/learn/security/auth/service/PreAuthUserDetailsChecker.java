package org.lsy.learn.security.auth.service;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * 自定义账号校验
 */
public class PreAuthUserDetailsChecker implements UserDetailsChecker {

    private MessageSourceAccessor message = SpringSecurityMessageSource.getAccessor();

    @Override
    public void check(UserDetails toCheck) {
        if (!toCheck.isAccountNonLocked()) {

            throw new LockedException(message.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
                    "您的账号已锁定，暂时不能登录！如需登录，请联系管理员！"));
        }

        if (!toCheck.isEnabled()) {

            throw new DisabledException(message.getMessage("AbstractUserDetailsAuthenticationProvider.disabled",
                    "User is disabled"));
        }

        if (!toCheck.isAccountNonExpired()) {

            throw new AccountExpiredException(message.getMessage("AbstractUserDetailsAuthenticationProvider.expired",
                    "User account has expired"));
        }
    }
}
