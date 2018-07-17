/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:06
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
    private String originUrl;
    /**
     * 生成自己的链接
     */
    private String targetUrl;
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
     * 是否转载链接的文章。0=否(手工编辑)，1=是(转载链接)
     */
    private Integer isLink;
    /**
     * 标签ID
     */
//    private List<Long> tagIds;
    /**
     * 是否发送。0=否，1=是
     */
    private Integer isSend;
    /**
     * 发送到服务号或微信。1=服务号，2=微信好友
     */
    private Integer sendToMp;
    /**
     * 接收文章的微信好友ID
     */
//    private List<Long> wxContactIds;
    /**
     * 是否添加名片。0=否，1=是
     */
    private Integer isAddVisitingCard;
    /**
     * 作者userId
     */
    private Long userId;
    /**
     * 公司ID
     */
    private Long corpId;
}
