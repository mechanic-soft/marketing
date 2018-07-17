/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午6:54
 */
package cn.com.geasy.marketing.domain.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 名片实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_visiting_card")
public class SysVisitingCard extends Entity implements Serializable {
    private static final long serialVersionUID = -6649019205724392941L;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 职位
     */
    @TableField("position")
    private String position;
    /**
     * 地址
     */
    @TableField("address")
    private String address;
    /**
     * 微信二维码路径
     */
    @TableField("qucode_url")
    private String qucodeUrl;
    /**
     * 公司ID
     */
    @TableField("corp_id")
    private Long corpId;

}
