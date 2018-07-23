/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 上午11:25
 */
package cn.com.geasy.marketing.domain.entity.system;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 日志实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_log")
public class SysLog extends Entity implements Serializable {
    /**
     * 真实姓名
     */
    private String username;

    /**
     * 菜单
     */
    private String menu;

    /**
     * URL
     */
    private String url;
    /**
     * 备注
     */
    private String remark;
}
