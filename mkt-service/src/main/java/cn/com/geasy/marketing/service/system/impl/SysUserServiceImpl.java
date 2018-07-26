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
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.ServiceException;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
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

    public SysUserDto findByUsernameOrWxUin(String username, Long wxUin) {
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username).or().eq("wx_uin", wxUin);
        List<SysUser> users = super.baseMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(users)){
            return SysUserMapstruct.getInstance.toDtoList(users).get(0);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer remove(List<Long> ids) {
        EntityWrapper<ReleUserRole> userRoleEntityWrapper = new EntityWrapper<>();
        userRoleEntityWrapper.in("user_id", ids);
        this.userRoleService.delete(userRoleEntityWrapper);
        return super.baseMapper.deleteBatchIds(ids);
    }

    public void updateByUsername(SysUserDto user) throws ServiceException{
        SysUserDto exist = baseMapper.findByWxUin(user.getWxUin());
        if (exist == null) {
            baseMapper.updateByUsername(user);
        }else {
            throw new ServiceException(HttpCode.SQL_DATA_ERROR, "微信UIN[" + user.getWxUin() + "]已存在。");
        }
    }

    public boolean insertOrUpdate(SysUserDto userDto) throws ServiceException{
        //验证用户名是否存在
        SysUserDto usernameExist = findByUsername(userDto.getUsername());
        if (usernameExist != null){
            throw new ServiceException(HttpCode.SQL_DATA_ERROR, "用户名[" + userDto.getUsername() + "]已存在");
        }
        //验证微信UIN是否存在
        SysUserDto wxUinExist = findByWxUin(userDto.getWxUin());
        if (wxUinExist != null){
            throw new ServiceException(HttpCode.SQL_DATA_ERROR, "微信UIN[" + userDto.getUsername() + "]已存在");
        }
        //密码加密
        String password = SecurityPasswordUtils.encrypt(userDto.getPassword());
        userDto.setPassword(password);

        SysUser user = SysUserMapstruct.getInstance.toEntity(userDto);
        return super.insertOrUpdate(user);
    }
}
