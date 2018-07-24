/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午7:13
 */
package cn.com.geasy.marketing.domain.entity.article;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章订阅表实体
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_subscription")
public class ArticleSubscription extends Entity implements Serializable {
    private static final long serialVersionUID = -8770908981436819464L;
    /**
     * 文章订阅表ID
     */
    private Long id;
    /**
     * 被订阅的人id
     */
    private Long subscriptionUserId;
    /**
     * 普通用户id
     */
    private Long userId;
    /**
     *订阅状态，0：默认，1：订阅
     */
    private Integer subscription;
    /**
     *状态
     */
    private Integer statues;
}
