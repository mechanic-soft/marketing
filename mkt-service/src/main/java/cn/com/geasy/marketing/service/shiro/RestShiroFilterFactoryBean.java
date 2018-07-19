///*
// * Copyright 2016-2018 the original author or authors.
// * Created on 2018/7/18 下午4:04
// */
//package cn.com.geasy.marketing.service.shiro;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.filter.mgt.FilterChainManager;
//import org.apache.shiro.web.filter.mgt.FilterChainResolver;
//import org.apache.shiro.web.mgt.WebSecurityManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.web.servlet.AbstractShiroFilter;
//import org.springframework.beans.factory.BeanInitializationException;
//
///**
// * 请在此写下该类的说明
// *
// * @author phil
// * @version 1.0.0
// */
//@Slf4j
//public class RestShiroFilterFactoryBean extends ShiroFilterFactoryBean {
//    public RestShiroFilterFactoryBean() {
//        super();
//    }
//
//    @Override
//    protected AbstractShiroFilter createInstance() throws Exception {
//        log.debug("Creating Shiro Filter instance.");
//        SecurityManager securityManager = this.getSecurityManager();
//        String msg;
//        if (securityManager == null) {
//            msg = "SecurityManager property must be set.";
//            throw new BeanInitializationException(msg);
//        } else if (!(securityManager instanceof WebSecurityManager)) {
//            msg = "The security manager does not implement the WebSecurityManager interface.";
//            throw new BeanInitializationException(msg);
//        } else {
//            FilterChainManager manager = this.createFilterChainManager();
//            RestPathMatchingFilterChainResolver chainResolver = new RestPathMatchingFilterChainResolver();
//            chainResolver.setFilterChainManager(manager);
//            return new RestShiroFilterFactoryBean.SpringShiroFilter((WebSecurityManager)securityManager, chainResolver);
//        }
//    }
//
//    private static final class SpringShiroFilter extends AbstractShiroFilter {
//        protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
//            if (webSecurityManager == null) {
//                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
//            } else {
//                this.setSecurityManager(webSecurityManager);
//                if (resolver != null) {
//                    this.setFilterChainResolver(resolver);
//                }
//
//            }
//        }
//    }
//}
