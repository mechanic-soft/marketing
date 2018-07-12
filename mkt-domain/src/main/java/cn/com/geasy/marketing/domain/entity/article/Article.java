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
@TableName("article")
public class Article extends Entity implements Serializable {
    private static final long serialVersionUID = 9048702973753977587L;
    /**
     * 转载文章的原链接
     */
    @TableField("origin_url")
    private String originUrl;

    /**
     * 生成自己的链接
     */
    private String url;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章的icon
     */
    private String icon;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章的描述
     */
    private String desc;

    /**
     * 作者userId
     */
    @TableField("user_id")
    private Long userId;
}
