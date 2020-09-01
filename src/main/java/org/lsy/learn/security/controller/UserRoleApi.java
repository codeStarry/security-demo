package org.lsy.learn.security.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.config.R;
import org.lsy.learn.security.model.UserRole;
import org.lsy.learn.security.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 系统管理 - 用户角色关联表  前端控制器
 *
 * @author lsy
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/user/role")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserRoleApi {

    private final IUserRoleService targetService;

    /**
     * 分页查询数据
     */
    @PostMapping(value = "/page")
    public R<IPage<UserRole>> pageUserRole(@RequestBody Pager<BaseParam<UserRole>> pager) {
        return R.ok(targetService.pageUserRole(pager));
    }

    /**
     * 查询全部数据
     */
    @PostMapping(value = "/list")
    public R<List<UserRole>> listUserRole() {
        return R.ok(targetService.list());
    }


    /**
     * 新增数据
     */
    @PostMapping(value = "/save")
    public R<Boolean> save(@RequestBody UserRole entity) {
        return R.ok(targetService.save(entity));
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    public R<Boolean> update(@RequestBody UserRole entity) {
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

