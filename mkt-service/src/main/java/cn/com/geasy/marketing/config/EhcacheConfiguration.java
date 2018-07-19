///*
// * Copyright 2016-2018 the original author or authors.
// * Created on 2018/7/11 下午1:30
// */
//package cn.com.geasy.marketing.config;
//
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
///**
// * Ehcache 配置
// *
// * @author phil
// * @version 1.0.0
// */
//@EnableCaching
//@Configuration
//public class EhcacheConfiguration {
//    /**
//     * 据shared与否的设置,
//     * Spring分别通过CacheManager.create() 或new CacheManager() 方式来创建一个 ehcache 基地.
//     * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
//     */
//    @Bean
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
//        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
//        ehCacheFactoryBean.setShared(true);
//        return ehCacheFactoryBean;
//    }
//
//    /**
//     * Spring ehcache 管理器
//     */
//    @Bean//(name = "ehCacheManager")
//    public EhCacheCacheManager springCacheManager() {
//        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
//        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
//        return ehCacheManager;
//    }
//
//    /**
//     * Shiro ehcache 管理器
//     */
//    @Bean
//    public EhCacheManager shiroCacheManager() {
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
//        return ehCacheManager;
//    }
//}
