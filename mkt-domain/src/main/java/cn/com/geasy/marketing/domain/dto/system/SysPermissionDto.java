/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午10:31
 */
package cn.com.geasy.marketing.domain.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色DTO
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPermissionDto implements Serializable {
    private static final long serialVersionUID = 6524488428741913060L;
    /**
     * ID
     */
    private Long id;
    /**
     * 端点资源地址
     */
    private String name;
    /**
     * 端点资源地址
     */
    private String endpoint;
    /**
     * HTTP 事件
     */
    private String method;
    /**
     * 描述
     */
    private String description;
    /**
     * 角色
     */
    private List<SysRoleDto> roles;
}
