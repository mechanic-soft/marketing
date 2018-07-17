/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午7:13
 */
package cn.com.geasy.marketing.domain.entity.wechat;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信联系人实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("wx_contact")
public class WxContact extends Entity implements Serializable {
    private static final long serialVersionUID = -886092645712427755L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 微信用户信息识别码(唯一)
     */
    private Long uin;
    /**
     * 加密的微信号(唯一)
     */
    private String username;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 头像URL
     */
    private Integer contactFlag;
    /**
     *
     */
    private Integer memberCount;
    /**
     *
     */
    private String memberList;
    /**
     *
     */
    private String remarkName;
    /**
     *
     */
    private Integer hideInputBarFlag;
    /**
     * 微信性别
     */
    private Integer sex;
    /**
     * 微信签名
     */
    private String signature;
    /**
     *
     */
    private Integer verifyFlag;
    /**
     *
     */
    private Integer ownerUin;
    /**
     *
     */
    private String pyInitial;
    /**
     *
     */
    private String pyQuanPin;
    /**
     *
     */
    private String remarkPyInitial;
    /**
     *
     */
    private String remarkPyQuanPin;
    /**
     *
     */
    private Integer starFriend;
    /**
     *
     */
    private Integer appAccountFlag;
    /**
     *
     */
    private Integer statues;
    /**
     *
     */
    private Integer AttrStatus;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     *
     */
    private String alias;
    /**
     *
     */
    private Integer snsFlag;
    /**
     *
     */
    private Integer uniFriend;
    /**
     *
     */
    private String DisplayName;
    /**
     *
     */
    private Integer chatRoomId;
    /**
     *
     */
    private String keyword;
    /**
     *
     */
    private String wncryChatRoomId;

}
