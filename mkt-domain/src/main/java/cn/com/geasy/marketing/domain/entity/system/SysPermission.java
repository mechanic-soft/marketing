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
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * 系统权限实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_permission")
public class SysPermission extends Entity implements Serializable {
    private static final long serialVersionUID = -5640366819272273166L;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 角色
     */
    @TableField(exist = false)
    private Set<SysRole> roles;

    public String getRolesName() {
        Set<SysRole> roles = getRoles();
        StringBuilder stringBuilder = new StringBuilder();
        for (SysRole role : roles) {
            stringBuilder.append("\"").append(role.getName()).append("\"").append(",");
        }
        if (StringUtils.isNoneBlank(stringBuilder)) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
