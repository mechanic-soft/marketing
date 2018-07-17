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
     * 登录账户
     */
    private String wxContactId;
    /**
     * 事件
     */
    private String mpUserId;
    /**
     * 发生日期
     */
    private String callTime;
    /**
     * 文章ID
     */
    private String customerCode;
    /**
     * 文章标题
     */
    private Long isAgreeAddWx;
    /**
     * 用户ID
     */
    private String wxId;
    /**
     * 用户ID
     */
    private String phone;
    /**
     * 用户ID
     */
    private String remark;
    /**
     * 用户ID
     */
    private String maturity;
    /**
     * 用户ID
     */
    private String isOpenAccount;
    /**
     * 用户ID
     */
    private String callcenterUserId;
    /**
     * 用户ID
     */
    private String corpId;
}
