/*
 * Copyright (c) 2004-2015 by KYD All rights reserved
 */
package cn.com.geasy.marketing.domain.entity.tag;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 标签文章关联实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("rele_tag_article")
public class TagArticle extends Entity implements Serializable {

    private static final long serialVersionUID = -4282729158471973935L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 标签所属的文章id
     */
    @TableField("article_id")
    private Long articleId;

    /**
     * 标签id
     */
    private Long tagId;

}