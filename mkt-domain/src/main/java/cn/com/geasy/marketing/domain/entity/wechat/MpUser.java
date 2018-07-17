/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:21
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
 * 服务号用户
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("mp_users")
public class MpUser extends Entity implements Serializable {
    private static final long serialVersionUID = 1258984513645291621L;
    /**
     * 昵称
     */
    private String mpNickname;
    /**
     * 头像URL
     */
    private String mpHeadImgUrl;
    /**
     * 服务号OPENID
     */
    private String mpOpenid;
    /**
     * 服务号UIUD
     */
    private String mpUuid;
    /**
     * 省
     */
    private String mpProvince;
    /**
     * 市
     */
    private String mpCity;
    /**
     * 县
     */
    private String mpCountry;
    /**
     * 地址
     */
    private String mpAddress;
    /**
     * 是否关注，0：未关注，1：关注
     */
    private Integer mpSubscribe;
}
