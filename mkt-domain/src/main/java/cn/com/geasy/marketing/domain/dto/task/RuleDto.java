/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:06
 */
package cn.com.geasy.marketing.domain.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 规则Dto
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleDto implements Serializable {
    private static final long serialVersionUID = -1112793586133713118L;
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
    private BigInteger startTime;

    /**
     * 结束日期
     */
    private BigInteger endTime;

    /**
     * 用户id
     */
   //private List<Long> userId;
/*    *//**
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
