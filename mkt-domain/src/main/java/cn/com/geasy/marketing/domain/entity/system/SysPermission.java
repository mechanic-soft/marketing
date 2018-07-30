/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午10:31
 */
package cn.com.geasy.marketing.domain.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;
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
     *
     */
    private Long pid;
    /**
     * 端点资源地址
     */
    private String name;
    /**
     * 端点资源地址
     */
    private String endpoint;
    /**
     * HTTP 事件
     */
    private String method;
    /**
     * 描述
     */
    private String description;
    /**
     * 角色
     */
    @TableField(exist = false)
    private Set<SysRole> roles;

    public String[] getRolesName() {
        List<SysRole> roles = Lists.newArrayList(getRoles());
        if (CollectionUtils.isEmpty(roles)){
            return null;
        }
        String[] rolesName = new String[roles.size()];
        for (int i = 0;i< roles.size(); i++) {
            SysRole role = roles.get(i);
            rolesName[i] = "ROLE_" + role.getName();
        }
        return rolesName;
    }
}
