/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/18 下午6:24
 */
package cn.com.geasy.marketing.domain.security;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 893935286308681878L;

    private Long id;
    private Long userId;
    private Long roleId;
}
