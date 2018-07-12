/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午10:42
 */
package cn.com.geasy.marketing.domain.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户DTO
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDto implements Serializable {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 登录账户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
}
