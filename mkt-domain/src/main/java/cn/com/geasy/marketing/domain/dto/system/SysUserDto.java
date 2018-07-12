/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午10:42
 */
package cn.com.geasy.marketing.domain.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户DTO
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDto implements Serializable {
    private static final long serialVersionUID = 6131455440052853807L;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 登录账户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
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
}
