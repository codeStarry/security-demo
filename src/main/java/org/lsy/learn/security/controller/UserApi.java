package org.lsy.learn.security.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.config.R;
import org.lsy.learn.security.domain.UserDTO;
import org.lsy.learn.security.model.User;
import org.lsy.learn.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 系统管理-用户基础信息表 前端控制器
 *
 * @author lsy
 * @since 2020-08-31
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserApi {

    private final IUserService targetService;

    /**
     * 分页查询数据
     */
    @PostMapping(value = "/page")
    public R<IPage<User>> pageUser(@RequestBody Pager<BaseParam<User>> pager) {
        return R.ok(targetService.pageUser(pager));
    }

    /**
     * 注册
     * @param entity
     * @return
     */
    @PostMapping(value = "/register")
    public R<Boolean> register(@RequestBody UserDTO entity) {
        return R.ok(targetService.register(entity));
    }

    /**
     * 查询全部数据
     */
    @PreAuthorize("@pms.hasPermission('普通用户')")
    @PostMapping(value = "/list")
    public R<List<User>> listUser() {
        return R.ok(targetService.list());
    }


    /**
     * 新增数据
     */
    @PostMapping(value = "/save")
    public R<Boolean> save(@RequestBody User entity) {
        return R.ok(targetService.save(entity));
    }

    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    public R<Boolean> update(@RequestBody User entity) {
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

