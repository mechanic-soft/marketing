/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午1:22
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysPermissionDto;
import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysPermissionService extends SuperService<SysPermission> {
    /**
     * 返回所有权限及其所关联角色名称
     *
     * @return List&lt;SysPermission&gt; 权限及其所关联角色名称
     */
    List<SysPermissionDto> findAllCaseRole();
    /**
     * 分页查询
     *
     * @param pageNum 页码
     * @return Page&lt;SysRoleDto&gt;
     */
    Page<SysPermissionDto> findDtoPage(int pageNum);
    /**
     * 按ID查询
     *
     * @param id 角色ID
     * @return SysRoleDto
     */
    SysPermissionDto findDtoById(Long id);
    /**
     * 删除权限，同时删除角色与权限关联信息
     *
     * @param ids 权限ID
     * @return Integer
     */
    Integer remove(List<Long> ids);

}
