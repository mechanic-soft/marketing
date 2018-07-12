/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午1:27
 */
package cn.com.geasy.marketing.config;

import cn.com.geasy.marketing.service.shiro.LoginRealm;
import com.gitee.mechanic.shiro.filter.RolesOrAuthorizationFilter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Map;

/**
 * Shiro 配置
 *
 * @author phil
 * @version 1.0.0
 */
@Slf4j
@Configuration
public class ShiroConfiguration {


//    @Autowired
//    private SysPermissionMapper permissionMapper;
//    @Autowired
//    private SysPermissionService permissionService;

    /**
     * 这是DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
     */
    @Bean(name = "loginRealm")
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public LoginRealm loginRealm(EhCacheManager shiroEhCacheManager) {
        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCacheManager(shiroEhCacheManager);
        loginRealm.setAuthorizationCachingEnabled(true);
        return loginRealm;
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @ConditionalOnMissingBean //设置Spring Boot仅仅在当前上下文中不存在该对象时，才会实例化
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(
            LoginRealm loginRealm,
            EhCacheManager shiroEhCacheManager) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(loginRealm);
        //用户授权/认证信息Cache, 采用EhCache 缓存
        dwsm.setCacheManager(shiroEhCacheManager);
        return dwsm;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager webSecurityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(webSecurityManager);
        return aasa;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    @Bean
    public RolesOrAuthorizationFilter rolesOrAuthorizationFilter(){
        return new RolesOrAuthorizationFilter();
    }

    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     *
     * @see ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            DefaultWebSecurityManager webSecurityManager,
            RolesOrAuthorizationFilter rolesOrAuthorizationFilter//,
//            @Qualifier("sysPermissionMapper") SysPermissionMapper permissionMapper
    ) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);
        shiroFilterFactoryBean.setLoginUrl("/unauthor");
        shiroFilterFactoryBean.setUnauthorizedUrl("/forbidden");

        //添加自定义条件过滤器
        Map<String, Filter> filtersMap = Maps.newHashMap();
        filtersMap.put(RolesOrAuthorizationFilter.NAME, rolesOrAuthorizationFilter);
        shiroFilterFactoryBean.setFilters(filtersMap);

//        Set<String> rolesName = permissionMapper.selectNamesByRolesId(Lists.newArrayList(1L,2L));
//
//        List<SysPermission> permissions = permissionMapper.selectList(null);
//
//        Map<String, String> chains = Maps.newLinkedHashMap();
//
//        //允许匿名访问微信公众平台认证接口
//        chains.put("/mp_token", "anon");
//
//        //允许匿名访问 H2 DB Console
//        chains.put("/h2*/**", "anon");
//
//        //允许匿名访问Swagger
//        chains.put("/swagger*/**", "anon");
//        chains.put("/v2/api-docs", "anon");
//        chains.put("/webjars/**", "anon");
//
//        //静态资源过滤器
//        chains.put("/*.ico", "anon");
//
//        //登录相关资源过滤器
//        chains.put("/login", "anon");
//        chains.put("/unauthor", "anon");
//        chains.put("/forbidden", "anon");
//        chains.put("/status", "anon");
//        chains.put("/logout", "logout");
//        chains.put("/wx/**", "anon");
////        chains.put("/product/attachement/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/holdings/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/netvalue/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/status/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/stocks/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/style/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/transaction/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/type/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/user/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/delete/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/product/list/**", "authc, rolesOr[\"supperadmin\",\"guest\"]");
////        chains.put("/product/save/**", "authc, rolesOr[\"supperadmin\"]");
////        chains.put("/reg/integral/*", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/questionnaire/**", "authc, rolesOr[\"supperadmin\",\"guest\"]");
////        chains.put("/reg/user/current", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/reg/user/update", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/reg/user/get/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/reg/login/**", "authc, rolesOr[\"supperadmin\",\"guest\"]");
////        chains.put("/reg/logout/**", "authc, rolesOr[\"supperadmin\",\"guest\"]");
////        chains.put("/reg/is_login/**", "authc, rolesOr[\"supperadmin\",\"guest\"]");
////        chains.put("/reg/product/list/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/reg/product/get/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
////        chains.put("/reg/integral/get/**", "authc, rolesOr[\"supperadmin\",\"reg\"]");
//
//        for (SysPermission permission : permissions) {
//            chains.put(permission.getName(), "authc, rolesOr[" + permission.getRolesName() + "]");
//        }
//
//        chains.put("/**", "authc");
//        log.debug(JsonMapper.INSTANCE.toJson(chains));

        //shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(EhCacheManager shiroEhCacheManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(shiroEhCacheManager);
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }
}
