///*
// * Copyright (c) 2004-2015 by KYD All rights reserved
// */
//package cn.com.geasy.marketing.domain.entity.system;
//
//import com.baomidou.mybatisplus.annotations.TableName;
//import com.gitee.mechanic.mybatis.base.Entity;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//
///**
// * 系统用户实体
// *
// * @author phil
// * @version 1.0.0
// */
//@Data
//@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor
//@TableName("sys_user")
//public class WechatUser extends Entity implements Serializable {
//
//    private static final long serialVersionUID = -1977457037933422110L;
//    /**
//     * 微信号在公众号的唯一标识
//     */
////    @JSONField(serialize=false)
//    private String openId;
//
//    /**
//     * 第三方的绑定标识
//     */
//    private String unionId;
//
//    /**
//     * 微信昵称
//     */
//    private String nickName;
//
//    /**
//     * 性别标识，0：未知，1：男性，2女性
//     */
//    private Integer sex;
//
//    /**
//     * 微信头像url
//     */
//    private String headImgUrl;
//
//    /**
//     * 县
//     */
//    private String country;
//
//    /**
//     * 省
//     */
//    private String province;
//
//    /**
//     * 城市
//     */
//    private String city;
//
//    /**
//     * 地址
//     */
//    private String address;
//
//    /**
//     * 是否关注，0：未关注，1：关注
//     */
//    private Integer subscribe;
//
//    /**
//     * 状态：1：是正常，2是删除
//     */
//    private Long state;
//
//    /**
//     * 所属的理财经理id
//     */
//    private Long ownerUserId;
//
//    /**
//     * 角色id
//     */
//    private Long roleId;
//}