/*
 * Copyright (c) 2004-2015 by KYD All rights reserved
 */
package cn.com.geasy.marketing.domain.entity.tag;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统模块实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tag")
public class Tag extends Entity implements Serializable {

    private static final long serialVersionUID = -2963452625509102556L;
    /**
     * 标签创建人
     */
    private Long userId;

    /**
     * 标签名
     */
    private String name;

    /**
     * 类别：1：文章标签，2：人的标签
     */
    private Long tagTypeId;

    /**
     * 标签来源 (0=微信,1=外呼)
     */
    private Integer tagSrc;

    /**
     * 是否系统标签(0=系统标签，1=自定义标签)
     */
    private Integer isSys;

    /**
     *
     */
    private String path;


}