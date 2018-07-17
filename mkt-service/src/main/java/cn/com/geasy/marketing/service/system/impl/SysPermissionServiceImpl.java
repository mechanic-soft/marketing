/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午1:23
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysPermissionMapper;
import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import cn.com.geasy.marketing.service.system.SysPermissionService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysPermissionServiceImpl extends SuperServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Override
    public List<SysPermission> selectAllCaseRole() {
        return super.baseMapper.selectAllCaseRole();
    }
}
