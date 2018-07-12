/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysRoleMapper;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import cn.com.geasy.marketing.service.system.SysRoleService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统用角色服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysRoleServiceImpl extends SuperServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
