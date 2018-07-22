/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/19 下午10:16
 */
package cn.com.geasy.marketing.security;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 访问决策管理。
 * 验证访问资源的所需的角色是否包含用户的角色，未包含无法通过验证。
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException { //configAttributes.contains("ROLE_NOPERMS")
        if (CollectionUtils.isEmpty(configAttributes)) {
            throw new AccessDeniedException("not allow");
        }

        for (ConfigAttribute ca : configAttributes) {
            String needRole = ((SecurityConfig) ca).getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (ga.getAuthority().equals(needRole)) {
                    //匹配到有对应角色,则允许通过
                    return;
                }
            }
        }
        //该url有配置权限,但是当然登录用户没有匹配到对应权限,则禁止访问
        throw new AccessDeniedException("Access is denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
