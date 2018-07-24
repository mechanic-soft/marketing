/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午5:18
 */
package cn.com.geasy.marketing.domain.dto.system;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单DTO
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuDto implements Serializable {

    private static final long serialVersionUID = 5054148149338355266L;
    /**
     * ID
     */
    private Long id;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 模块标签
     */
    private String url;
    /**
     * 权限ID
     */
    private Long permissionId;
    /**
     * 子菜单
     */
    private List<SysMenuDto> subItems = Lists.newArrayList();

}
