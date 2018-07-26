/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午1:23
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysPermissionMapper;
import cn.com.geasy.marketing.domain.dto.system.SysPermissionDto;
import cn.com.geasy.marketing.domain.entity.system.ReleRolePermission;
import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import cn.com.geasy.marketing.mapstruct.system.SysPermissionMapstruct;
import cn.com.geasy.marketing.service.system.ReleRolePermissionService;
import cn.com.geasy.marketing.service.system.SysPermissionService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysPermissionServiceImpl extends SuperServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    private final ReleRolePermissionService rolePermissionService;

    @Autowired
    public SysPermissionServiceImpl(ReleRolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public List<SysPermissionDto> findAllCaseRole() {
        return super.baseMapper.findAllCaseRole();
    }

    @Override
    public Page<SysPermissionDto> findDtos(int pageNum) {
        Page<SysPermissionDto> page = PageUtils.getPage(pageNum);
        List<SysPermission> permissions = baseMapper.selectPage(page, null);
        List<SysPermissionDto> permissionDtos = SysPermissionMapstruct.getInstance.toDtoList(permissions);
        return PageUtils.getPage(page, permissionDtos);
    }

    @Override
    public List<SysPermissionDto> findDtos( ) {
        List<SysPermission> permissions = baseMapper.selectList(null);
        return SysPermissionMapstruct.getInstance.toDtoList(permissions);
    }

    @Override
    public SysPermissionDto findDtoById(Long id) {
        return SysPermissionMapstruct.getInstance.toDto(baseMapper.selectById(id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer remove(List<Long> ids) {

        //删除角色与权限关联信息
        EntityWrapper<ReleRolePermission> rolePermissionWrapper = new EntityWrapper<>();
        rolePermissionWrapper.in("permission_id", ids);
        this.rolePermissionService.delete(rolePermissionWrapper);

        //删除角色信息
        return super.baseMapper.deleteBatchIds(ids);
    }
}
