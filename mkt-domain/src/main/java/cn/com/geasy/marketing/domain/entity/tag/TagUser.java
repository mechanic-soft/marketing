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
 * 标签用户关联实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("rele_tag_user")
public class TagUser extends Entity implements Serializable {

    private static final long serialVersionUID = 5256507528197985833L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 标签所属的人id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 标签id
     */
    private Long tagId;

}