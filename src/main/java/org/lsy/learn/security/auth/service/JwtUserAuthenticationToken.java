package org.lsy.learn.security.auth.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 自定义登录token，控制由哪个Provider处理登录逻辑
 */
@Getter
@Setter
public class JwtUserAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUid = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;
    private String username;
    private String password;

    public JwtUserAuthenticationToken(String username, String password) {
        super(null);
        this.principal = username;
        setAuthenticated(false);
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public JwtUserAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
