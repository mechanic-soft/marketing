/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:18
 */
package cn.com.geasy.marketing.domain.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统菜单实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends Entity implements Serializable {
    private static final long serialVersionUID = -2512166677542096840L;

    /**
     * 父级ID
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 模块标签
     */
    private String url;
    /**
     * 权限ID
     */
    private Long permissionId;
}
