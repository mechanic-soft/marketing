/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysUserMapper;
import cn.com.geasy.marketing.domain.entity.system.ReleUserRole;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import cn.com.geasy.marketing.service.system.ReleUserRoleService;
import cn.com.geasy.marketing.service.system.SysUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
    public SysUser findByUsername(String username) {
        return super.baseMapper.findByUsername(username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer remove(List<Long> ids) {
        EntityWrapper<ReleUserRole> userRoleEntityWrapper = new EntityWrapper<>();
        userRoleEntityWrapper.in("user_id", ids);
        this.userRoleService.delete(userRoleEntityWrapper);
        return super.baseMapper.deleteBatchIds(ids);
    }
}
