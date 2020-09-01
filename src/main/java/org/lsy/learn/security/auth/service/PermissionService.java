package org.lsy.learn.security.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 自定义用户权限校验
 */
@Component("pms")
public class PermissionService {

    /**
     * 判断用户是否具有该权限
     *
     * @param permissions
     * @return
     */
    public Boolean hasPermission(String...permissions) {

        boolean flag = false;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<String> roleNames = authentication.getAuthorities().stream()
                                                                .map(GrantedAuthority::getAuthority)
                                                                .collect(Collectors.toList());

        for (int i = 0; i < roleNames.size(); i++) {
            if (PatternMatchUtils.simpleMatch(permissions, roleNames.get(i))) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.isNull(authentication)) {

            return null;
        }

        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();

        return userDetails.getId();
    }
}
