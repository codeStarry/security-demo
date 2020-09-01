package org.lsy.learn.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.domain.UserDTO;
import org.lsy.learn.security.model.User;

/**
 * 系统管理-用户基础信息表 服务类
 *
 * @author lsy
 * @since 2020-08-31
 */
public interface IUserService extends IService<User> {

    /**
     * 根据条件分页查询数据
     *
     * @param pager
     * @return IPage
     */
    IPage<User> pageUser(Pager<BaseParam<User>> pager);

    /**
     * 注册
     *
     * @param entity
     * @return
     */
    Boolean register(UserDTO entity);
}
