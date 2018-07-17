/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:53
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 系统权限数据访问接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysPermissionMapper extends SuperMapper<SysPermission> {
    /**
     * 返回匹配指定多个角色ID的权限名称
     *
     * @param rolesId 角色ID
     * @return Set&lt;String&gt; 权限名称
     */
    Set<String> selectNamesByRolesId(@Param("rolesId") List<Long> rolesId);
    /**
     * 返回所有权限及其所关联角色名称
     *
     * @return List&lt;SysPermission&gt; 权限及其所关联角色名称
     */
    List<SysPermission> selectAllCaseRole();
}
