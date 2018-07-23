/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/19 下午4:01
 */
package cn.com.geasy.marketing.security;

import cn.com.geasy.marketing.dao.system.SysPermissionMapper;
import cn.com.geasy.marketing.domain.dto.system.SysPermissionDto;
import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 从数据库中读取权限
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        AntPathRequestMatcher matcher;

        List<SysPermissionDto> permissions = permissionMapper.findAllCaseRole();
        for(SysPermissionDto p : permissions) {
            String endpoint = p.getEndpoint();
            String method = p.getMethod();
            List<SysRoleDto> roles = p.getRoles();
            String[] rolesName = new String[roles.size()];
            for (int i=0; i < roles.size(); i++){
                rolesName[i] = roles.get(i).getName();
            }
            matcher = new AntPathRequestMatcher(endpoint, method);
            if (matcher.matches(request)) {
                return SecurityConfig.createList(rolesName);
            }
        }


//        List<SysPermission> permissions = permissionMapper.findAllCaseRole();
//        for(SysPermission p : permissions) {
//            String endpoint = p.getEndpoint();
//            String method = p.getMethod();
//            String[] rolesName = p.getRolesName();
//            matcher = new AntPathRequestMatcher(endpoint, method);
//            if (matcher.matches(request)) {
//                return SecurityConfig.createList(rolesName);
//            }
//        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
