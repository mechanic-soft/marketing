/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 上午11:56
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
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("rele_menu_permission")
public class ReleMenuPermission extends Entity implements Serializable {
    private static final long serialVersionUID = 5840166973258897586L;
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 权限ID
     */
    private Long permission_id;
}
