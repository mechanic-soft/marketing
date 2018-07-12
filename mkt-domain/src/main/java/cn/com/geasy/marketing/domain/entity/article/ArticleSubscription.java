/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:06
 */
package cn.com.geasy.marketing.domain.entity.article;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_subscription")
public class ArticleSubscription extends Entity implements Serializable {
    private static final long serialVersionUID = -5003219213978950342L;
    /**
     * 被订阅的人id
     */
    @TableField("subscription_user_id")
    private Long subscriptionUserId;

    /**
     * 普通用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 订阅状态，0：默认，1：订阅
     */
    private Integer subscription;
}
