/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 下午3:18
 */
package cn.com.geasy.marketing.dao.system;

import cn.com.geasy.marketing.domain.dto.system.SysMenuDto;
import cn.com.geasy.marketing.domain.entity.system.SysMenu;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysMenuMapper extends SuperMapper<SysMenu> {
    /**
     * 查返回匹配角色的菜单
     *
     * @param id     从该ID递归向下查询
     * @param roleId 角色ID
     * @return List&lt;SysMenuDto&gt;
     */
    List<SysMenuDto> findByRolesId(@Param("id") Long id, @Param("roleId") Long roleId);

//    SysMenuDto findDtoById(@Param("id") Long id);
//    List<SysMenuDto> findDtoById(@Param("id") Long id);
}
