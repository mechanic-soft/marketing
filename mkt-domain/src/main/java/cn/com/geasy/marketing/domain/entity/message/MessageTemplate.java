/*
 * Copyright (c) 2004-2015 by KYD All rights reserved
 */
package cn.com.geasy.marketing.domain.entity.message;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 模版实体类
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("message_template")
public class MessageTemplate extends Entity implements Serializable {

    private static final long serialVersionUID = 122374076675432019L;
    /**
     * first描述
     */
    private String firstMsg;

    /**
     * 内容
     */
    private String content;

    /**
     * remark描述
     */
    private String remarkMsg;

    /**
     * 超链接
     */
    private String url;

    /**
     * 更新人id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;
}