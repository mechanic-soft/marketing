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
 * 文章喜欢状态实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_like")
public class ArticleLike extends Entity implements Serializable {
    private static final long serialVersionUID = 2615159054091456334L;
    /**
     * 文章id
     */
    @TableField("article_id")
    private Long articleId;

    /**
     * 用户
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 喜欢状态。0：默认，1：喜欢
     */
    private Integer like;
}
