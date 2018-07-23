/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/20 上午10:20
 */
package cn.com.geasy.marketing.service.security;

import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentUser extends User implements Serializable {
    private static final long serialVersionUID = 4775140300820540555L;

    /**
     * ID
     */
    private Long id;
    /**
     * 登录账户
     */
    private String username;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 微信用户信息识别码(唯一)
     */
    private Long wxUin;
    /**
     * 加密的微信号(唯一)
     */
    private String wxUsername;
    /**
     * 微信昵称
     */
    private String wxNickname;
    /**
     * 头像URL
     */
    private String wxHeadImgUrl;
    /**
     * 微信性别。1=男，2=女
     */
    private Integer wxSex;
    /**
     * 微信签名
     */
    private String wxSignature;
    /**
     * 服务号OpenID
     */
    private String mpOpenid;
    /**
     * 服务号UUID
     */
    private String mpUuid;

    private List<SysRoleDto> roles;

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CurrentUser(String username,
                       String password,
                       Collection<? extends GrantedAuthority> authorities,
                       Long id,
                       String realName,
                       Long wxUin,
                       String wxUsername,
                       String wxNickname,
                       String wxHeadImgUrl,
                       Integer wxSex,
                       String wxSignature,
                       String mpOpenid,
                       String mpUuid,
                       List<SysRoleDto> roles
    ) {
        this(username, password, authorities);
        this.id = id;
        this.realName = realName;
        this.wxUin = wxUin;
        this.wxUsername = wxUsername;
        this.wxNickname = wxNickname;
        this.wxHeadImgUrl = wxHeadImgUrl;
        this.wxSex = wxSex;
        this.wxSignature = wxSignature;
        this.mpOpenid = mpOpenid;
        this.mpUuid = mpUuid;
        this.roles = roles;
    }

    public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
