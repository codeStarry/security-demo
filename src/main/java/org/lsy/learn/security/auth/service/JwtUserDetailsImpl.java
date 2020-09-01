package org.lsy.learn.security.auth.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.lsy.learn.security.model.Role;
import org.lsy.learn.security.model.User;
import org.lsy.learn.security.model.UserRole;
import org.lsy.learn.security.service.IRoleService;
import org.lsy.learn.security.service.IUserRoleService;
import org.lsy.learn.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 实现类，对登录用户进行认证
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JwtUserDetailsImpl implements CustomizeUserDetailsService{

    private final IUserService userService;

    private final IUserRoleService userRoleService;

    private final IRoleService roleService;

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(Wrappers.<User>query().lambda().eq(User::getUsername, username));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        List<Role> roles = this.queryUserRole(user.getId());

        return new JwtUserDetails(user).setRoles(roles);
    }

    /**
     * 根据ID查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public JwtUserDetails findById(Integer userId) {
        User user = userService.getById(userId);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        List<Role> roles = this.queryUserRole(user.getId());

        return new JwtUserDetails(user).setRoles(roles);
    }

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> queryUserRole(Integer userId) {

        List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>query().lambda().eq(UserRole::getUserId, userId));
        if (Objects.isNull(userRoles)) {

            //throw new Authorization
        }
        List<Integer> ids = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        return roleService.listByIds(ids);
    }
}
