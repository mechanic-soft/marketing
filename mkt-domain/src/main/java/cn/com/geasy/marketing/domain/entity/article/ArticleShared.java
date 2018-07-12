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
@TableName("article_shared")
public class ArticleShared extends Entity implements Serializable {
    private static final long serialVersionUID = -2508554014684202286L;
    /**
     * 上游分享人
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 文章id
     */
    @TableField("article_id")
    private Long articleId;

    /**
     * 分享人
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 状态，0：正常，1：删除
     */
    private Integer state;

    /**
     * 分享状态 0：未分享成功，1：已经分享
     */
    @TableField("share_status")
    private Integer shareStatus;
}
