/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:48
 */
package cn.com.geasy.marketing.domain.entity.system;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统用户实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser extends Entity implements Serializable {
    private static final long serialVersionUID = 5238493534518596624L;
    /**
     * 登录账户
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 加密盐值
     */
    private String salt;
    /**
     * 真实姓名
     */
//    @TableField("real_name")
    private String realName;
    /**
     * 微信用户信息识别码(唯一)
     */
//    @TableField("wx_uin")
    private Long wxUin;
    /**
     * 加密的微信号(唯一)
     */
//    @TableField("wx_username")
    private String wxUsername;
    /**
     * 微信昵称
     */
//    @TableField("wx_nickname")
    private String wxNickname;
    /**
     * 头像URL
     */
//    @TableField("wx_head_img_url")
    private String wxHeadImgUrl;
    /**
     * 微信性别。1=男，2=女
     */
//    @TableField("wx_sex")
    private Integer wxSex;
    /**
     * 微信签名
     */
//    @TableField("wx_signature")
    private String wxSignature;
    /**
     * 服务号OpenID
     */
//    @TableField("mp_openid")
    private String mpOpenid;
    /**
     * 服务号UUID
     */
//    @TableField("mp_uuid")
    private String mpUuid;
}
