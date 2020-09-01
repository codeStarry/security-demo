package org.lsy.learn.security.mapper;

import org.springframework.stereotype.Repository;


import org.lsy.learn.security.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 系统管理-角色表  Mapper 接口
 * @author lsy
 * @since 2020-08-31
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

}
