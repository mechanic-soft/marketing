/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/20 上午11:27
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.ReleRolePermissionMapper;
import cn.com.geasy.marketing.domain.entity.system.ReleRolePermission;
import cn.com.geasy.marketing.service.system.ReleRolePermissionService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色与权限关联服务实现
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class ReleRolePermissionServiceImpl extends SuperServiceImpl<ReleRolePermissionMapper, ReleRolePermission> implements ReleRolePermissionService {
}
