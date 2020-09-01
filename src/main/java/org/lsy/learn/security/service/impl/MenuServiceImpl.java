package org.lsy.learn.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.Translations;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.mapper.MenuMapper;
import org.lsy.learn.security.model.Menu;
import org.lsy.learn.security.service.IMenuService;
import org.springframework.stereotype.Service;


/**
 * 系统管理-权限资源表  服务实现类
 *
 * @author lsy
 * @since 2020-08-31
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    /**
     * 根据条件查询数据列表
     *
     * @param pager
     * @return
     */
    @Override
    public IPage<Menu> pageMenu(Pager<BaseParam<Menu>> pager) {
        return baseMapper.selectPage(Pager.decorateBaseParam(pager), Translations.translationBaseParam(pager.getCondition()));
    }
}
