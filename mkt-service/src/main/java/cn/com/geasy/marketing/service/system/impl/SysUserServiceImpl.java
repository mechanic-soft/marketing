/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysUserMapper;
import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.domain.entity.system.ReleUserRole;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import cn.com.geasy.marketing.mapstruct.system.SysUserMapstruct;
import cn.com.geasy.marketing.service.system.ReleUserRoleService;
import cn.com.geasy.marketing.service.system.SysRoleService;
import cn.com.geasy.marketing.service.system.SysUserService;
import cn.com.geasy.marketing.utils.SecurityPasswordUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.ServiceException;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ListIterator;

/**
 * 系统用户服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final ReleUserRoleService userRoleService;
    private final SysRoleService roleService;


    @Autowired
    public SysUserServiceImpl(ReleUserRoleService userRoleService, SysRoleService roleService) {
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }

    @Override
    public Page<SysUserDto> findDtos(int pageNum) {
        Page<SysUser> page = PageUtils.getPage(pageNum);
        page = super.selectPage(page);
        List<SysUserDto> userDtos = SysUserMapstruct.getInstance.toDtoList(page.getRecords());

        ListIterator<SysUserDto> iterator = userDtos.listIterator();
        while (iterator.hasNext()) {
            SysUserDto userDto = iterator.next();
            userDto.setRoles(roleService.findDtoByUserId(userDto.getId()));
            iterator.set(userDto);
        }
        return PageUtils.getPage(page, userDtos);
    }

    @Override
    public List<SysUserDto> findDtos() {
        List<SysUser> users = super.selectList();
        List<SysUserDto> userDtos = SysUserMapstruct.getInstance.toDtoList(users);

        ListIterator<SysUserDto> iterator = userDtos.listIterator();
        while (iterator.hasNext()) {
            SysUserDto userDto = iterator.next();
            userDto.setRoles(roleService.findDtoByUserId(userDto.getId()));
            iterator.set(userDto);
        }
        return userDtos;
    }

    @Override
    public SysUserDto findDtoById(Long id) {
        SysUser user = super.selectById(id);
        SysUserDto userDto = SysUserMapstruct.getInstance.toDto(user);
        userDto.setRoles(roleService.findDtoByUserId(userDto.getId()));
        return userDto;
    }

    @Override
    public SysUserDto findByUsername(String username) {
        return super.baseMapper.findByUsername(username);
    }

    @Override
    public SysUserDto findByWxUin(Long wxUin) {
        return super.baseMapper.findByWxUin(wxUin);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer remove(List<Long> ids) {
        EntityWrapper<ReleUserRole> userRoleEntityWrapper = new EntityWrapper<>();
        userRoleEntityWrapper.in("user_id", ids);
        this.userRoleService.delete(userRoleEntityWrapper);
        return super.baseMapper.deleteBatchIds(ids);
    }

    public void updateByUsername(SysUserDto userDto) throws ServiceException {
        if (wxUinIsExist(userDto.getWxUin(), userDto.getUsername())) {
            throw new ServiceException(HttpCode.SQL_DATA_ERROR, "微信UIN[" + userDto.getWxUin() + "]已存在。");
        }
        baseMapper.updateByUsername(userDto);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public boolean insertOrUpdate(SysUserDto userDto) throws ServiceException {

        if (usernameOrWxUinIsExist(userDto)) {
            throw new ServiceException(HttpCode.SQL_DATA_ERROR, "用户名或微信UIN已存在");
        }

        //密码加密
        String password = SecurityPasswordUtils.encrypt(userDto.getPassword());
        userDto.setPassword(password);

        //保存用户
        SysUser user = SysUserMapstruct.getInstance.toEntity(userDto);
        boolean success = super.insertOrUpdate(user);
        if (success) {
            Long userId = user.getId();
            //删除该用户的所有角色关联
            Wrapper<ReleUserRole> userRoleWrapper = new EntityWrapper<>();
            userRoleWrapper.eq("user_id", userId);
            success = this.userRoleService.delete(userRoleWrapper);

            //保存该用户的所有角色关联
            List<SysRoleDto> roleDtos = userDto.getRoles();
            for (SysRoleDto roleDto : roleDtos) {
                Long roleId = roleDto.getId();

                ReleUserRole userRole = new ReleUserRole();
                userRole.setRoleId(userId);
                userRole.setUserId(roleId);
                success = this.userRoleService.insert(userRole);
            }
        }
        return success;
    }

    /**
     * 验证UIN是否存在
     *
     * @param wxUin 微信UIN
     * @return 存在返回true，不存在返回false
     */
    public boolean wxUinIsExist(Long wxUin) {
        return wxUinIsExist(wxUin, null);
    }

    /**
     * 验证UIN是否存在
     *
     * @param wxUin    微信UIN
     * @param username 用户名
     * @return 存在返回true，不存在返回false
     */
    public boolean wxUinIsExist(Long wxUin, String username) {
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("wx_uin", wxUin);
        if (StringUtils.isNotBlank(username)) {
            wrapper.ne("username", username);
        }
        return super.selectOne(wrapper) != null;
    }

    /**
     * 验证用户名或UIN是否存在
     *
     * @param userDto 用户信息
     * @return 存在返回true，不存在返回false
     */
    public boolean usernameOrWxUinIsExist(SysUserDto userDto) {

        Wrapper<SysUser> userWrapper = new EntityWrapper<>();
        String username = userDto.getUsername();
        Long wxUin = userDto.getWxUin();
        Long id = userDto.getId();
        if (StringUtils.isBlank(username) && wxUin == null) {
            throw new ServiceException(HttpCode.PARAMS_ERROR, "参数 username、wxUin 不能同时为空");
        }

        if (id != null) {
            userWrapper.ne("id", id);
        }

        if (StringUtils.isNotBlank(username) && wxUin != null) {
            userWrapper.where("(username={0} OR wx_uin={1})", userDto.getUsername(), userDto.getWxUin());
        } else if (StringUtils.isNotBlank(username)) {
            userWrapper.where("username={0}", userDto.getUsername());
        } else if (wxUin != null) {
            userWrapper.where("wx_uin={0}", userDto.getWxUin());
        }

        return super.selectOne(userWrapper) != null;
    }
}
