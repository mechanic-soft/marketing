/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysUserMapper;
import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.domain.entity.system.ReleUserRole;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import cn.com.geasy.marketing.mapstruct.system.SysUserMapstruct;
import cn.com.geasy.marketing.service.system.ReleUserRoleService;
import cn.com.geasy.marketing.service.system.SysUserService;
import cn.com.geasy.marketing.utils.SecurityPasswordUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.ServiceException;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final ReleUserRoleService userRoleService;

    @Autowired
    public SysUserServiceImpl(ReleUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public SysUserDto findByUsername(String username) {
        return super.baseMapper.findByUsername(username);
    }

    @Override
    public SysUserDto findByWxUin(Long wxUin) {
        return super.baseMapper.findByWxUin(wxUin);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer remove(List<Long> ids) {
        EntityWrapper<ReleUserRole> userRoleEntityWrapper = new EntityWrapper<>();
        userRoleEntityWrapper.in("user_id", ids);
        this.userRoleService.delete(userRoleEntityWrapper);
        return super.baseMapper.deleteBatchIds(ids);
    }

    public void updateByUsername(SysUserDto user){
         baseMapper.updateByUsername(user);
    }

    public boolean insertOrUpdate(SysUserDto userDto){
        SysUserDto exist = findByUsername(userDto.getUsername());
        if (exist != null){
            throw new ServiceException(HttpCode.SQL_DATA_ERROR, "用户名" + userDto.getUsername() + "已存在");
        }
        //密码加密
        String password = SecurityPasswordUtils.encrypt(userDto.getPassword());
        userDto.setPassword(password);

        SysUser user = SysUserMapstruct.getInstance.toEntity(userDto);
        return super.insertOrUpdate(user);
    }
}
