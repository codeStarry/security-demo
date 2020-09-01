package org.lsy.learn.security.auth.service;

import org.lsy.learn.security.model.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * 对UserDetailsService进行扩展，增加自定义逻辑
 */
public interface CustomizeUserDetailsService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    JwtUserDetails findById(Integer userId);

    List<Role> queryUserRole(Integer userId);
}
