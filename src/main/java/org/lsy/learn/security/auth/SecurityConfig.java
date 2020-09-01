package org.lsy.learn.security.auth;

import org.lsy.learn.security.auth.exception.ResourceAuthExceptionEntryPoint;
import org.lsy.learn.security.auth.filter.JwtTokenAuthenticationFilter;
import org.lsy.learn.security.auth.filter.JwtUserAuthenticationFilter;
import org.lsy.learn.security.auth.handler.JwtUserAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security配置
 * @EnableGlobalMethodSecurity(prePostEnabled = true) 开启权限注解支持
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ResourceAuthExceptionEntryPoint entryPoint;

    @Autowired
    private JwtUserAccessDeniedHandler accessDeniedHandler;

    @Lazy
    @Autowired
    private JwtUserAuthenticationFilter authenticationFilter;

    @Autowired
    private JwtTokenAuthenticationFilter tokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.exceptionHandling().authenticationEntryPoint(entryPoint);
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.authorizeRequests()
                .antMatchers("/login", "/user/register").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
