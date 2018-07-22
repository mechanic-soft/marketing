/*
 * Copyright (c) 2004-2015 by KYD All rights reserved
 */
package cn.com.geasy.marketing.domain.entity.task;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 规则客户标签关联表
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("rele_rule_customer_label")
public class RuleCustomerLabel extends Entity implements Serializable {
    private static final long serialVersionUID = -8265266935452914580L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 规则外键
     */
    private Long ruleId;

    /**
     *标签外键
     */
    private Long tagId;

    /**
     * 状态(0=删除,1=正常)
     */
    private Integer status;


}