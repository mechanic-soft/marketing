/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:53
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.entity.system.SysUser;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户数据访问接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysUserMapper extends SuperMapper<SysUser> {
    /**
     * 返回匹配指定登录账户的用户
     *
     * @param username 登录账户
     * @return SysUser 用户
     */
    SysUser findByUsername(@Param("username") String username);
}
