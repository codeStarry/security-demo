package org.lsy.learn.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.security.component.Pager;
import org.lsy.learn.security.component.Translations;
import org.lsy.learn.security.component.param.BaseParam;
import org.lsy.learn.security.config.CustomizeException;
import org.lsy.learn.security.domain.UserDTO;
import org.lsy.learn.security.mapper.UserMapper;
import org.lsy.learn.security.model.User;
import org.lsy.learn.security.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * 系统管理-用户基础信息表 服务实现类
 *
 * @author lsy
 * @since 2020-08-31
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 根据条件查询数据列表
     *
     * @param pager
     * @return
     */
    @Override
    public IPage<User> pageUser(Pager<BaseParam<User>> pager) {
        return baseMapper.selectPage(Pager.decorateBaseParam(pager), Translations.translationBaseParam(pager.getCondition()));
    }

    /**
     * 注册
     *
     * @param entity
     * @return
     */
    @Override
    public Boolean register(UserDTO entity) {
        String pwd = entity.getPassword();
        String confirmPwd = entity.getConfirmPassword();
        if (!Objects.equals(pwd, confirmPwd)) {
            throw new CustomizeException("两次密码输入不一致");
        }
        String salt = BCrypt.gensalt();
        String password = BCrypt.hashpw(pwd, salt);

        return this.save(entity.defv().setPassword(password).setSalt(salt).setSex("0").setPwd(pwd));
    }
}
