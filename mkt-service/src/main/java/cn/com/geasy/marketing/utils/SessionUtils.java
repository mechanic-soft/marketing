/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/20 上午10:37
 */
package cn.com.geasy.marketing.utils;

import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import cn.com.geasy.marketing.service.security.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Session 工具类
 *
 * @author phil
 * @version 1.0.0
 */
public class SessionUtils {
    /**
     * 获取当前登录用户信息
     *
     * @return CurrentUser 登录用户信息
     */
    public static CurrentUser getUser() {
        return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户ID
     *
     * @return Long 当前登录用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户的角色ID
     */
    public static Long getRoleId(){
        List<Long> roleIds = getUser().getRoles().stream().map(SysRoleDto::getId).collect(Collectors.toList());
        return roleIds.get(0);
    }
}
