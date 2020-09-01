package org.lsy.learn.security.mapper;

import org.springframework.stereotype.Repository;


import org.lsy.learn.security.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 系统管理-权限资源表  Mapper 接口
 * @author lsy
 * @since 2020-08-31
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

}
