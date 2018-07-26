/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:53
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色数据访问接口
 *
 * @author phil
 * @version 1.0.0
 */
@Repository
public interface SysRoleMapper extends SuperMapper<SysRole> {
    /**
     * 返回匹配指定用户ID的角色
     *
     * @param userId 用户ID
     * @return List&lt;SysRole&gt; 角色
     */
    List<SysRole> findByUserId(Long userId);

    /**
     * 返回匹配指定权限ID的角色
     *
     * @param permissionId 权限ID
     * @return List&lt;SysRole&gt; 角色
     */
    List<SysRole> selectByPermissionId(@Param("permissionId") Long permissionId);

    /**
     * 返回匹配指定多个权限ID的角色
     *
     * @param permissionsId 权限ID
     * @return List&lt;SysRole&gt; 角色
     */
    List<SysRole> selectByPermissionsId(@Param("permissionsId") List<Long> permissionsId);

    /**
     * 返回及联权限的角色分页信息
     *
     * @param wrapper 查询条件
     * @param pagination 分页参数
     * @return Page&lt;SysRole&gt;
     */
    List<SysRoleDto> findDtos(@Param("ew") Wrapper<SysRoleDto> wrapper, Pagination pagination);

    /**
     * 返回及联权限的角色分页信息
     *
     * @return List&lt;SysRole&gt;
     */
    List<SysRoleDto> findDtos(@Param("ew") Wrapper<SysRoleDto> wrapper);


    /**
     * 返回及联权限的角色分页信息
     *
     * @param wrapper 查询条件
     * @return SysRole
     */
    SysRoleDto findDto(@Param("ew") Wrapper<SysRoleDto> wrapper);
}
