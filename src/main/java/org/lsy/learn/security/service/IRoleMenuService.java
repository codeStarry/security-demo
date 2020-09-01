package org.lsy.learn.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.model.RoleMenu;

/**
 * 系统管理 - 角色-权限资源关联表  服务类
 *
 * @author lsy
 * @since 2020-08-31
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 根据条件分页查询数据
     *
     * @param pager
     * @return IPage
     */
    IPage<RoleMenu> pageRoleMenu(Pager<BaseParam<RoleMenu>> pager);
}
