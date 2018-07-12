/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.entity.system.SysUser;
import com.gitee.mechanic.mybatis.base.SuperService;

/**
 * 系统用户服务接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysUserService extends SuperService<SysUser> {
    /**
     * 返回匹配指定登录账户的用户
     *
     * @param username 登录账户
     * @return SysUser 用户
     */
    SysUser findByUsername(String username);
}
