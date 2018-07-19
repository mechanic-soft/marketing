/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午10:31
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
import java.util.List;

/**
 * 系统角色实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole  extends Entity implements Serializable {
    private static final long serialVersionUID = -854434094539614531L;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;

    @TableField(exist = false)
    private List<SysPermission> permissions;
}
