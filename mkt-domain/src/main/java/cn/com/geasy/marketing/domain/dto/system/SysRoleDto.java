/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午10:31
 */
package cn.com.geasy.marketing.domain.dto.system;

import com.google.common.collect.Lists;
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
public class SysRoleDto implements Serializable {
    private static final long serialVersionUID = 5901693123941715728L;
    /**
     * ID
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 权限
     */
    private List<SysPermissionDto> permissions = Lists.newArrayList();
}
