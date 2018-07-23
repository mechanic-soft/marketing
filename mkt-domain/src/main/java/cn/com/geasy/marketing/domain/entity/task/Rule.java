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
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * 规则实体
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("rule")
public class Rule extends Entity implements Serializable {
    private static final long serialVersionUID = 2468893508369585337L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    /**
     * 状态(0=删除,1=正常)
     */
    private Integer status;

    /**
     * 开始日期
     */
    private LocalDate startDate;
    /**
     * 结束日期
     */
    private LocalDate endDate;
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
