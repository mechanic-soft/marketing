///*
// * Copyright 2016-2018 the original author or authors.
// * Created on 2018/7/10 下午10:29
// */
//package cn.com.geasy.marketing.service.shiro;
//
//import cn.com.geasy.marketing.dao.system.SysPermissionMapper;
//import cn.com.geasy.marketing.dao.system.SysRoleMapper;
//import cn.com.geasy.marketing.dao.system.SysUserMapper;
//import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
//import cn.com.geasy.marketing.domain.entity.system.SysRole;
//import cn.com.geasy.marketing.domain.entity.system.SysUser;
//import cn.com.geasy.marketing.mapstruct.system.SysUserMapstruct;
//import com.google.common.collect.Lists;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Set;
//
///**
// * 请在此写下该类的说明
// *
// * @author phil
// * @version 1.0.0
// */
//@Slf4j
////@Component
//public class LoginRealm extends AuthorizingRealm {
//
//    /**
//     * 密码加密方式
//     */
//    public final static String ALGORITHM = "SHA-256";
//    /**
//     * 密码加密迭代次数
//     */
//    public final static int HASH_ITTERATIONS = 3;
//
//    @Autowired
//    //这里切记不可注入涉及事务的Service，会导致事务失效！！！
//    private SysUserMapper userMapper;
//    @Autowired
//    private SysRoleMapper roleMapper;
//    @Autowired
//    private SysPermissionMapper permissionMapper;
//
//    private AuthorizationInfo authorizationInfo;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //获取当前登录输入的用户名，等价于(String) principals.getPrimaryPrincipal();
//        String username = (String) super.getAvailablePrincipal(principals);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        if (this.authorizationInfo == null) {
//            SysUser user = findUserByUsername(username);
//
//            if (user == null) {
//                return null;
//            }
//
//            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            List<SysRole> roles = roleMapper.selectByUserId(user.getId());
//
//            List<Long> rolesId = Lists.newArrayList();
//            //为Shiro赋予权限
//            for (SysRole role : roles) {
//                info.addRole(role.getName());
//                rolesId.add(role.getId());
//            }
//
//            Set<String> permissions = permissionMapper.selectNamesByRolesId(rolesId);
//            info.setStringPermissions(permissions);
//            log.debug("authorizationInfo空时刷新系统权限，刷新用户：" + username);
//            return info;
//        } else {
//            info = (SimpleAuthorizationInfo) this.authorizationInfo;
//            this.authorizationInfo = null;
//        }
//
//        log.debug("刷新系统权限，刷新用户：" + username);
//        return info;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        //获取用户登录信息
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//
//        log.info("当前用户：" + ReflectionToStringBuilder.toString(
//                token,
//                ToStringStyle.MULTI_LINE_STYLE
//        ));
//        String username = usernamePasswordToken.getUsername();
//        SysUser user = findUserByUsername(username);
//        if (user == null) {
//            return null;
//        }
//        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
//        //设置用户session
//        SysUserDto userDto = SysUserMapstruct.getInstance.toDto(user);
//        Session session = SecurityUtils.getSubject().getSession();
//        session.setAttribute("user", userDto);
//
//        //将账户、密码、盐值以及姓名存放到登录认证info中进行密码对比校验
//        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), credentialsSalt, getName());
//    }
//
//    @PostConstruct
//    public void initCredentialsMatcher() {
//        // 采用SHA-256加密
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(ALGORITHM);
//        hashedCredentialsMatcher.setHashIterations(HASH_ITTERATIONS);
//        setCredentialsMatcher(hashedCredentialsMatcher);
//    }
//
//    /**
//     * 重载用户授权信息
//     *
//     * @param authorizationInfo 用户授权信息
//     */
//    public void reloadAuthorizationInfo(AuthorizationInfo authorizationInfo) {
//        this.authorizationInfo = authorizationInfo;
//        this.doGetAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
//    }
//
//    /**
//     * 清除用户授权信息
//     *
//     * @param principals 用户凭据
//     */
//    public void clearAuthorizationInfo(PrincipalCollection principals) {
//        clearCachedAuthorizationInfo(principals);
//    }
//
//    /**
//     * 清除所有用户授权信息缓存.
//     */
//    public void clearAllCachedAuthorizationInfo() {
//        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//        if (cache != null) {
//            for (Object key : cache.keys()) {
//                cache.remove(key);
//            }
//        }
//    }
//
//    /**
//     * 根据用户名查找用户信息
//     *
//     * @param username 用户名
//     * @return 用户信息
//     */
//    private SysUser findUserByUsername(String username) {
//        SysUser query = new SysUser();
//        query.setUsername(username);
//        return this.userMapper.selectOne(query);
//    }
//}
