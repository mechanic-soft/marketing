/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午1:21
 */
package cn.com.geasy.marketing.service.shiro;

import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import cn.com.geasy.marketing.service.system.SysPermissionService;
import cn.com.geasy.marketing.service.system.SysRoleService;
import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.ServiceException;
import com.gitee.mechanic.shiro.filter.RolesOrAuthorizationFilter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Shiro 权限过滤器服务
 *
 * @author phil
 * @version 1.0.0
 */
@Slf4j
@Service
public class ShiroService {
    private final ShiroFilterFactoryBean shiroFilterFactoryBean;
    private final SysPermissionService permissionService;
    private final SysRoleService roleService;
    private final LoginRealm loginRealm;

    @Autowired
    public ShiroService(
            ShiroFilterFactoryBean shiroFilterFactoryBean,
            SysPermissionService permissionService,
            SysRoleService roleService,
            LoginRealm loginRealm) {
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.loginRealm = loginRealm;
    }

    /**
     * 重载权限信息
     */
    public void reloadFilterChains() {
        AbstractShiroFilter shiroFilter = null;
        synchronized (this.shiroFilterFactoryBean) {
            try {
                shiroFilter = (AbstractShiroFilter) this.shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new ServiceException(HttpCode.INTERNAL_SERVER_ERROR, "Shiro 初始化权限时时发生错误。");
            }
            //获取过滤管理器
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager chainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            //清空权限配置
            chainManager.getFilterChains().clear();
            this.shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

            //重新生成权限配置
            Map<String, String> filterChains = this.getFilterChains();

            this.shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChains);

            if (!CollectionUtils.isEmpty(filterChains)) {
                filterChains.forEach((key, filterChain) -> {
                    chainManager.createChain(key, filterChain.trim().replace(" ", ""));
                });
            }

            //logger.debug(JsonMapper.INSTANCE.toJson(this.shiroFilterFactoryBean.getFilterChainDefinitionMap()));
        }
    }

    /**
     * 生成权限信息
     *
     * @return 权限信息
     */
    private Map<String, String> getFilterChains() {

        List<SysPermission> permissions = permissionService.selectList(null);
        Map<String, String> chains = Maps.newLinkedHashMap();

        //允许匿名访问微信公众平台认证接口
        chains.put("/mp_token", "anon");

        //允许匿名访问 H2 DB Console
        chains.put("/h2*/**", "anon");

        //允许匿名访问Swagger
        chains.put("/swagger*/**", "anon");
        chains.put("/v2/api-docs", "anon");
        chains.put("/webjars/**", "anon");

        //静态资源过滤器
        chains.put("/*.ico", "anon");

        //登录相关资源过滤器
        chains.put("/login", "anon");
        chains.put("/unauthor", "anon");
        chains.put("/forbidden", "anon");
        chains.put("/logout", "logout");
        chains.put("/wx/**", "anon");

        for (SysPermission permission : permissions) {
            String value = MessageFormat.format(
                    "authc, {0}[{1}]",
                    RolesOrAuthorizationFilter.NAME,
                    permission.getRolesName()
            );
            chains.put(permission.getName(), value);
        }

        chains.put("/**", "authc");
        return chains;
    }

    /**
     * 添加角色当前用户在Shiro中的授权信息中
     *
     * @param roles 角色
     */
    public void addRoles(Collection<String> roles) {
        AuthorizationInfo info = getAuthorizationInfo();
        info.getRoles().addAll(roles);
        loginRealm.reloadAuthorizationInfo(info);
    }

    /**
     * 删除用户在Shiro中授权信息中的角色
     *
     * @param roles 角色
     */
    public void removeRoles(Collection<String> roles) {
        AuthorizationInfo info = getAuthorizationInfo();
        info.getRoles().removeAll(roles);
        loginRealm.reloadAuthorizationInfo(info);
    }

    /**
     * 获取当前用户授权信息
     *
     * @return 当前用户授权信息
     */
    private AuthorizationInfo getAuthorizationInfo() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        AuthorizationInfo info = this.loginRealm.getAuthorizationCache().get(principals);
        if (info == null) {
            info = this.loginRealm.doGetAuthorizationInfo(principals);
        }
        loginRealm.clearAuthorizationInfo(principals);
        return info;
    }
}
