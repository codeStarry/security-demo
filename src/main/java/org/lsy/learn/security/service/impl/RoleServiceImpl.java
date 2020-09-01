package org.lsy.learn.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.Translations;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.mapper.RoleMapper;
import org.lsy.learn.security.model.Role;
import org.lsy.learn.security.service.IRoleService;
import org.springframework.stereotype.Service;


/**
 * 系统管理-角色表  服务实现类
 *
 * @author lsy
 * @since 2020-08-31
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 根据条件查询数据列表
     *
     * @param pager
     * @return
     */
    @Override
    public IPage<Role> pageRole(Pager<BaseParam<Role>> pager) {
        return baseMapper.selectPage(Pager.decorateBaseParam(pager), Translations.translationBaseParam(pager.getCondition()));
    }
}
