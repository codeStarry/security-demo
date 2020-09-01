package org.lsy.learn.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.model.UserRole;

/**
 * 系统管理 - 用户角色关联表  服务类
 *
 * @author lsy
 * @since 2020-08-31
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据条件分页查询数据
     *
     * @param pager
     * @return IPage
     */
    IPage<UserRole> pageUserRole(Pager<BaseParam<UserRole>> pager);
}
