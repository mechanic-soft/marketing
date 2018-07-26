/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/18 下午6:33
 */
package cn.com.geasy.marketing.service.security;

import cn.com.geasy.marketing.dao.system.SysRoleMapper;
import cn.com.geasy.marketing.dao.system.SysUserMapper;
import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import cn.com.geasy.marketing.mapstruct.system.SysRoleMapstruct;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义的 Security UserService。从数据库中读取当前用户信息，并写到Security。
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDto user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户【" + username + "】不存在");
        }

        List<SysRole> roles = roleMapper.findByUserId(user.getId());
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for (SysRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return new CurrentUser(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getId(),
                user.getRealName(),
                user.getWxUin(),
                user.getWxUsername(),
                user.getWxNickname(),
                user.getWxHeadImgUrl(),
                user.getWxSex(),
                user.getWxSignature(),
                user.getMpOpenid(),
                user.getMpUuid(),
                SysRoleMapstruct.getInstance.toDtoList(roles)
        );
    }
}
