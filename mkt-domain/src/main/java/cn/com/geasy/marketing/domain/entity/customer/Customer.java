/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午7:02
 */
package cn.com.geasy.marketing.domain.entity.customer;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户信息实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("customer")
public class Customer extends Entity implements Serializable {
    private static final long serialVersionUID = 267795432213577189L;

    /**
     * 微信联系人ID
     */
    private Long wxContactId;
    /**
     * 服务号用户ID
     */
    private String mpUserId;
    /**
     * 呼叫时间(同步)
     */
    private String callTime;
    /**
     * 用户编码(同步)
     */
    private String customerCode;
    /**
     * 是否同意添加微信(同步)
     */
    private Long isAgreeAddWx;
    /**
     * 微信号(同步)
     */
    private String wxId;
    /**
     * 手机号(同步)
     */
    private String phone;
    /**
     * 备注(同步)
     */
    private String remark;
    /**
     * 客户成熟度
     */
    private String maturity;
    /**
     * 是否开户
     */
    private String isOpenAccount;
    /**
     * 外呼平台用户ID
     */
    private String callcenterUserId;
    /**
     * 公司ID
     */
    private String corpId;

    /**
     * 用户ID
     */
    private Long userId;
}
