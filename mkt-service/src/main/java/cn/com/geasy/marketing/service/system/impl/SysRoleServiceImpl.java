/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysRoleMapper;
import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import cn.com.geasy.marketing.domain.entity.system.ReleRolePermission;
import cn.com.geasy.marketing.domain.entity.system.ReleUserRole;
import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import cn.com.geasy.marketing.mapstruct.system.SysPermissionMapstruct;
import cn.com.geasy.marketing.mapstruct.system.SysRoleMapstruct;
import cn.com.geasy.marketing.service.system.ReleRolePermissionService;
import cn.com.geasy.marketing.service.system.ReleUserRoleService;
import cn.com.geasy.marketing.service.system.SysPermissionService;
import cn.com.geasy.marketing.service.system.SysRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用角色服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysRoleServiceImpl extends SuperServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final ReleUserRoleService userRoleService;
    private final ReleRolePermissionService rolePermissionService;
    private final SysPermissionService permissionService;

    @Autowired
    public SysRoleServiceImpl(ReleUserRoleService userRoleService, ReleRolePermissionService rolePermissionService, SysPermissionService permissionService) {
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
        this.permissionService = permissionService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer remove(List<Long> ids) {
        //删除角色与用户关联信息
        EntityWrapper<ReleUserRole> userRoleWrapper = new EntityWrapper<>();
        userRoleWrapper.in("role_id", ids);
        this.userRoleService.delete(userRoleWrapper);

        //删除角色与权限关联信息
        EntityWrapper<ReleRolePermission> rolePermissionWrapper = new EntityWrapper<>();
        rolePermissionWrapper.in("role_id", ids);
        this.rolePermissionService.delete(rolePermissionWrapper);

        //删除角色信息
        return super.baseMapper.deleteBatchIds(ids);
    }

    public Page<SysRoleDto> findDtoPage(int pageNum) {
        Page<SysRoleDto> page = PageUtils.getPage(pageNum);
        List<SysRoleDto> roleDtos = baseMapper.findDtoPage(null, page);
        return page.setRecords(roleDtos);
    }

    @Override
    public SysRoleDto findDtoById(Long id) {

        Wrapper<SysRoleDto> wrapper = new EntityWrapper<>();
        wrapper.eq("sr.id", id);

        //查询角色
        return baseMapper.findDto(wrapper);
    }

    /**
     * 返回匹配指定用户ID的角色
     *
     * @param userId 用户ID
     * @return List&lt;SysRole&gt; 角色
     */
    public List<SysRoleDto> findDtoByUserId(Long userId){
        return SysRoleMapstruct.getInstance.toDtoList(baseMapper.findByUserId(userId));
    }

    /**
     * 返回将包含关联权限的角色
     * @param role 角色信息
     * @return SysRoleDto
     */
    private SysRoleDto findPermissions(SysRole role){
        SysRoleDto roleDto = SysRoleMapstruct.getInstance.toDto(role);

        //从关联表查询权限ID
        EntityWrapper<ReleRolePermission> rrpWrapper = new EntityWrapper<>();
        rrpWrapper.setSqlSelect("permission_id")
                .eq("role_id", role.getId());
        List<Object> permissionIds = this.rolePermissionService.selectObjs(rrpWrapper);

        if (CollectionUtils.isNotEmpty(permissionIds)) {
            //查询权限
            EntityWrapper<SysPermission> spWrapper = new EntityWrapper<>();
            spWrapper.in("id", permissionIds);
            List<SysPermission> permissions = this.permissionService.selectList(spWrapper);

            if (CollectionUtils.isNotEmpty(permissions)) {
                roleDto.setPermissions(SysPermissionMapstruct.getInstance.toDtoList(permissions));
            }
        }
        return roleDto;
    }
}
