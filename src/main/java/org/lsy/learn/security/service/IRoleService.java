package org.lsy.learn.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.model.Role;

/**
 * 系统管理-角色表  服务类
 *
 * @author lsy
 * @since 2020-08-31
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据条件分页查询数据
     *
     * @param pager
     * @return IPage
     */
    IPage<Role> pageRole(Pager<BaseParam<Role>> pager);
}
