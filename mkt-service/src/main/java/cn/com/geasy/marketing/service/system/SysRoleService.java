/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 系统角色服务接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysRoleService extends SuperService<SysRole> {
    /**
     * 删除角色，同时删除角色与权限、角色与用户关联信息
     *
     * @param ids 权限ID
     * @return Integer
     */
    Integer remove(List<Long> ids);

    /**
     * 分页查询，同时级联权限
     * @param pageNum 页码
     * @return Page&lt;SysRoleDto&gt;
     */
    Page<SysRoleDto> findDtoPage(int pageNum);

    /**
     * 查询角色，同时级联权限
     * @return List&lt;SysRoleDto&gt;
     */
    List<SysRoleDto> findDtoAll();
    /**
     * 按ID查询角色
     * @param id 角色ID
     * @return SysRoleDto
     */
    SysRoleDto findDtoById(Long id);

//    /**
//     * 按用户ID查询角色
//     * @param userIds 用户ID
//     * @return List&lt;SysRoleDto&gt;
//     */
//    List<SysRoleDto> findByUserIds(List<Long> userIds);
    /**
     * 返回匹配指定用户ID的角色
     *
     * @param userId 用户ID
     * @return List&lt;SysRole&gt; 角色
     */
    List<SysRoleDto> findDtoByUserId(Long userId);
    /**
     * 返回匹配指定用户ID的角色
     *
     * @param userId 用户ID
     * @return List&lt;SysRole&gt; 角色
     */
    public List<SysRole> findByUserId(Long userId);
}
