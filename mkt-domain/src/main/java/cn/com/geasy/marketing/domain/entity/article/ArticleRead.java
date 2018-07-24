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
import java.time.LocalDateTime;

/**
 * 文章阅读表实体
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_read")
public class ArticleRead extends Entity implements Serializable {
    private static final long serialVersionUID = 6237867192849034482L;
    /**
     * 文章阅读ID
     */
    private Long id;
    /**
     * 分享信息的id
     */
    private Long sharedInfoId;
    /**
     * 文章id
     */
    private Long articleInfoId;
    /**
     * 读者id
     */
    private Long userId;
    /**
     *开始阅读时间
     */
    private LocalDateTime startTime;
    /**
     *结束阅读时间
     */
    private LocalDateTime endTime;
    /**
     * 阅读时长
     */
    private Long readingTime;
    /**
     *状态
     */
    private Integer statues;
}
