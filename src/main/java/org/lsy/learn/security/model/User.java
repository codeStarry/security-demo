package org.lsy.learn.security.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统管理-用户基础信息表
 *
 * @author lsy
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
public class User extends Model<User> {


    private static final long serialVersionUID = 1L;

    public User defv() {
        setGmtCreate(LocalDateTime.now());
        setGmtModified(getGmtCreate());
        setFlag(1);
        return this;
    }

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    @TableField("username")
    private String username;

    /**
     * 登录密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别 0:男 1:女
     */
    @TableField("sex")
    private String sex;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 状态
     */
    @TableField("flag")
    private Integer flag;

    /**
     * 盐值
     */
    @TableField("salt")
    private String salt;

    /**
     * token
     */
    @TableField("token")
    private String token;

    /**
     * QQ 第三方登录Oppen_ID唯一标识
     */
    @TableField("qq_oppen_id")
    private String qqOppenId;

    /**
     * 明文密码
     */
    @TableField("pwd")
    private String pwd;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
