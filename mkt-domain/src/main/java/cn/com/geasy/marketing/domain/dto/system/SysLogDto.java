/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 上午11:25
 */
package cn.com.geasy.marketing.domain.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 日志DTO
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLogDto implements Serializable {
    /**
     * ID
     */
    private Long id;
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
