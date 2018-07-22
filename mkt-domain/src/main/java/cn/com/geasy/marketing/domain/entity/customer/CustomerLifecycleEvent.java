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
import java.util.Date;

/**
 * 客户生命周期事件实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("customer_lifecycle_event")
public class CustomerLifecycleEvent extends Entity implements Serializable {
    private static final long serialVersionUID = 2677954329137767188L;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 事件(0=呼叫,1=加微,2=转发,3=开户,4=阅读,5=联系)
     */
    private Integer event;

    /**
     * 事件日期
     */
    private Date eventDate;

    /**
     * 用户ID
     */
    private Long userId;




}
