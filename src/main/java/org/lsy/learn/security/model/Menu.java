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
 * 系统管理-权限资源表
 *
 * @author lsy
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_menu")
public class Menu extends Model<Menu> {


    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 上级资源ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * url
     */
    @TableField("url")
    private String url;

    /**
     * 资源编码
     */
    @TableField("resources")
    private String resources;

    /**
     * 资源名称
     */
    @TableField("title")
    private String title;

    /**
     * 资源级别
     */
    @TableField("level")
    private Integer level;

    /**
     * 排序
     */
    @TableField("sort_no")
    private Integer sortNo;

    /**
     * 资源图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 类型 menu、button
     */
    @TableField("type")
    private String type;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

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
