/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 下午3:38
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysMenuDto;
import cn.com.geasy.marketing.domain.entity.system.SysMenu;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 菜单服务接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysMenuService extends SuperService<SysMenu> {
    /**
     * 返回匹配当前用户的菜单
     *
     * @return List&lt;SysMenuDto&gt;
     */
    List<SysMenuDto> findByRolesId();

    /**
     * 返回指定ID及所有下级菜单
     *
     * @param id 菜单ID
     * @return SysMenuDto
     */
    SysMenuDto findDtoById(Long id);

    /**
     * 递归删除
     *
     * @param id id 菜单ID
     * @return boolean
     */
    boolean recursiveDelete(Long id);
}
