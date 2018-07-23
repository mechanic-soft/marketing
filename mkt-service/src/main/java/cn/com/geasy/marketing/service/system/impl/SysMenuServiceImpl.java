/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 下午3:39
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysMenuMapper;
import cn.com.geasy.marketing.domain.dto.system.SysMenuDto;
import cn.com.geasy.marketing.domain.entity.system.SysMenu;
import cn.com.geasy.marketing.mapstruct.system.SysMenuMapstruct;
import cn.com.geasy.marketing.service.system.SysMenuService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单服务实现
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysMenuServiceImpl extends SuperServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenuDto> findByRolesId() {
        return baseMapper.findByRolesId(0L, SessionUtils.getRoleId());
    }

    @Override
    public SysMenuDto findDtoById(Long id) {
        SysMenu menu = super.selectById(id);
        SysMenuDto menuDto = SysMenuMapstruct.getInstance.toDto(menu);

        List<SysMenuDto> subMenuDtos = baseMapper.findByRolesId(id, SessionUtils.getRoleId());
        menuDto.setSubItems(subMenuDtos);
        return menuDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public boolean recursiveDelete(Long id) {
        List<SysMenuDto> subMenus = baseMapper.findByRolesId(id, SessionUtils.getRoleId());
        for (SysMenuDto menu : subMenus) {
            if (menu.getParentId() > 0) {
                //递归删除节点
                this.recursiveDelete(menu.getId());
            } else {
                super.deleteById(menu.getId());
            }
        }
        return super.deleteById(id);
    }
}
