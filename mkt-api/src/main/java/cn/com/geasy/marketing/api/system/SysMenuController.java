/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 下午3:47
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.domain.dto.system.SysMenuDto;
import cn.com.geasy.marketing.domain.entity.system.SysMenu;
import cn.com.geasy.marketing.mapstruct.system.SysMenuMapstruct;
import cn.com.geasy.marketing.service.system.SysMenuService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单接口
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Menu", description = "菜单接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1/sys")
public class SysMenuController {

    private final SysMenuService menuService;

    @Autowired
    public SysMenuController(SysMenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "当前用户菜单列表")
    @GetMapping(path = "/menus/user_menus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getUserMenus() {
        return ResponseUtils.result(this.menuService.findByRolesId());
    }

    @ApiOperation(value = "菜单列表")
    @GetMapping(path = "/menus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getMenus() {
        return ResponseUtils.result(this.menuService.findByRolesId());
    }

    @ApiOperation(value = "菜单详情")
    @GetMapping(path = "/menus/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> get(@PathVariable(required = true) Long id){
        return ResponseUtils.result(this.menuService.findDtoById(id));
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping(path = "/menus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> insert(@RequestBody(required = true) SysMenuDto SysMenuDto){
        SysMenu SysMenu = SysMenuMapstruct.getInstance.toEntity(SysMenuDto);
        this.menuService.insert(SysMenu);
        SysMenuDto savedRoleDto = SysMenuMapstruct.getInstance.toDto(SysMenu);
        return ResponseUtils.result(savedRoleDto);
    }

    @ApiOperation(value = "更新菜单")
    @PatchMapping(path = "/menus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> updatge(@RequestBody(required = true) SysMenuDto SysMenuDto){
        SysMenu SysMenu = SysMenuMapstruct.getInstance.toEntity(SysMenuDto);
        this.menuService.updateById(SysMenu);
        SysMenuDto savedMenuDto = SysMenuMapstruct.getInstance.toDto(SysMenu);
        return ResponseUtils.result(savedMenuDto);
    }

    @ApiOperation(value = "删除菜单")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "菜单ID", paramType = "path")
    })
    @DeleteMapping(path = "/menus/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@PathVariable Long id) {
        return ResponseUtils.result(this.menuService.recursiveDelete(id));
    }
}
