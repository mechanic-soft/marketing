/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 下午2:09
 */
package cn.com.geasy.marketing.domain.dto.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公司DTO
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysCorpDto implements Serializable {
    private static final long serialVersionUID = -8552790719319927725L;
    /**
     * ID
     */
    private Long id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
