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
import java.time.LocalDateTime;

/**
 * 客户动态实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("customer_dynamic")
public class CustomerDynamic extends Entity implements Serializable {
    private static final long serialVersionUID = 267795432213577189L;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 事件
     */
    private Integer event;
    /**
     * 发生日期
     */
    private LocalDateTime eventDate;
    /**
     * 文章ID
     */
    private Long articleId;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文章标签
     */
    private String articleTag;
    /**
     * 阅读时长/秒
     */
    private Integer readTime;
    /**
     * 是否阅读全文
     * （0=否，1=是）
     */
    private Integer isFullRead;
}
