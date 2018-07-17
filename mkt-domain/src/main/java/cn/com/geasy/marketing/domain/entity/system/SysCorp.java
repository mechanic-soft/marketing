/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 下午2:07
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
 * 公司信息实体
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_corp")
public class SysCorp  extends Entity implements Serializable {
    private static final long serialVersionUID = -616499967255703904L;
    /**
     * 公司名称
     */
    private String name;
}
