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
import java.util.Date;

/**
 * 文章阅读信息实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_read")
public class ArticleRead extends Entity implements Serializable {
    private static final long serialVersionUID = 7623737818808026463L;
    /**
     * 分享信息的id
     */
    @TableField("shared_id")
    private Long sharedId;

    /**
     * 文章id
     */
    @TableField("article_id")
    private Long articleId;

    /**
     * 读者id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 开始阅读时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束阅读时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 阅读时长
     */
    @TableField("reading_time")
    private Integer readingTime;
}
