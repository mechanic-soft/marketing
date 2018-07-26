/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/26 上午10:11
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.service.system.LoginService;
import cn.com.geasy.marketing.service.system.SysUserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * 登录服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final SysUserService userService;

    public LoginServiceImpl(SysUserService userService) {
        this.userService = userService;
    }

    @Override
    public SysUserDto authenticate(SysUserDto userDto) throws AuthenticationException {
        SysUserDto user = this.userService.findByWxUin(userDto.getWxUin());

        return user;
    }
}
