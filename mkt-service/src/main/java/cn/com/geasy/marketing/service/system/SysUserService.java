/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

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
    SysUserDto findByUsername(String username);

    /**
     * 返回匹配微信UIN的用户
     *
     * @param wxUin 微信UIN
     * @return SysUser 用户
     */
    SysUserDto findByWxUin(Long wxUin);

    /**
     * 删除用户，同时删除用户与角色关联信息
     *
     * @param ids 用户ID
     * @return Boolean
     */
    Integer remove(List<Long> ids);

    /**
     * 按用户名更新用户微信信息
     *
     * @param user 用户信息
     */
    void updateByUsername(SysUserDto user);

    /**
     * 保存用户信息。保存时会检测用户名是否存在。
     *
     * @return boolean
     */
    boolean insertOrUpdate(SysUserDto user);
}
