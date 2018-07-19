///*
// * Copyright 2016-2018 the original author or authors.
// * Created on 2018/7/19 下午3:51
// */
//package cn.com.geasy.marketing.service.security;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
///**
// * 请在此写下该类的说明
// *
// * @author phil
// * @version 1.0.0
// */
//@Service
//public class MyAccessDecisionManager implements AccessDecisionManager {
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//        if(null== configAttributes || configAttributes.size() <=0) {
//            return;
//        }
//        ConfigAttribute c;
//        String needRole;
//        for (ConfigAttribute configAttribute : configAttributes) {
//            c = configAttribute;
//            needRole = c.getAttribute();
//            for (GrantedAuthority ga : authentication.getAuthorities()) {//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
//                if (needRole.trim().equals(ga.getAuthority())) {
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("no right");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute attribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true;
//    }
//}
