/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:53
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统用户数据访问接口
 *
 * @author phil
 * @version 1.0.0
 */
@Repository
public interface SysUserMapper extends SuperMapper<SysUser> {
    /**
     * 返回匹配指定登录账户的用户
     *
     * @param username 登录账户
     * @return SysUser 用户
     */
    SysUserDto findByUsername(@Param("username") String username);

    /**
     * 返回匹配微信UIN的用户
     *
     * @param wxUin 微信UIN
     * @return SysUser 用户
     */
    SysUserDto findByWxUin(Long wxUin);

    /**
     * 按用户名更新用户微信信息
     *
     * @param user 用户信息
     */
    void updateByUsername(@Param("user") SysUserDto user);
}
