/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:06
 */
package cn.com.geasy.marketing.domain.entity.task;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 规则触发行为实体
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("rule_trigger_action")
public class RuleTriggerAction extends Entity implements Serializable {

    private static final long serialVersionUID = 571814800879835098L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 规则外键
     */
    private Long ruleId;

    /**
     * 行为(0=阅读,1=订阅,3=聊天)
     */
    private Integer action;

    /**
     * 条件(0= >,1= < ,2= >=,3= <=,4= =)
     */
    private Integer condition;

    /**
     * 次数
     */
    private Integer frequency;

    /**
     * 状态(0=删除,1=正常)
     */
    private Integer status;

    /**
     * 创建记录的用户id
     *//*
    private BigInteger createUserId;
    *//**
     * 创建记的时间
     *//*
    private LocalDateTime createTime;
    *//**
     * 记录最后一次更新的用户id
     *//*
    private BigInteger updateUserId;
    *//**
     * 更改记录的时间
     *//*
    private LocalDateTime updateTime;*/
}
