package org.lsy.learn.security.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.config.R;
import org.lsy.learn.security.model.RoleMenu;
import org.lsy.learn.security.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 系统管理 - 角色-权限资源关联表  前端控制器
 *
 * @author lsy
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/role/menu")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RoleMenuApi {

    private final IRoleMenuService targetService;

    /**
     * 分页查询数据
     */
    @PostMapping(value = "/page")
    public R<IPage<RoleMenu>> pageRoleMenu(@RequestBody Pager<BaseParam<RoleMenu>> pager) {
        return R.ok(targetService.pageRoleMenu(pager));
    }

    /**
     * 查询全部数据
     */
    @PostMapping(value = "/list")
    public R<List<RoleMenu>> listRoleMenu() {
        return R.ok(targetService.list());
    }


    /**
     * 新增数据
     */
    @PostMapping(value = "/save")
    public R<Boolean> save(@RequestBody RoleMenu entity) {
        return R.ok(targetService.save(entity));
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    public R<Boolean> update(@RequestBody RoleMenu entity) {
        return R.ok(targetService.updateById(entity));
    }

    /**
     * 删除
     */
    @PostMapping(value = "/del")
    public R<Boolean> delete(@RequestBody List<Long> ids) {
        return R.ok(targetService.removeByIds(ids));
    }


}

