/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/26 上午10:11
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import org.springframework.security.core.AuthenticationException;

/**
 * 登录服务接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface LoginService {
    SysUserDto authenticate(SysUserDto userDto)
            throws AuthenticationException;
}
