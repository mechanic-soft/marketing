/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/22 下午2:18
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import cn.com.geasy.marketing.mapstruct.system.SysRoleMapstruct;
import cn.com.geasy.marketing.service.system.SysRoleService;
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

import java.util.List;

/**
 * 角色接口
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Role", description = "角色接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1/sys")
public class SysRoleController {
    private final SysRoleService sysRoleService;

    @Autowired
    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @ApiOperation(value = "获取角色")
    @GetMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getRoles(@RequestParam(defaultValue = "0") int pageNum){
        return ResponseUtils.result(this.sysRoleService.findDtoPage(pageNum));
    }

    @ApiOperation(value = "角色详情")
    @GetMapping(path = "/roles/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> get(@PathVariable(required = true) Long id){
        return ResponseUtils.result(this.sysRoleService.findDtoById(id));
    }

    @ApiOperation(value = "新增角色")
    @PostMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> insert(@RequestBody(required = true) SysRoleDto sysRoleDto){
        SysRole sysRole = SysRoleMapstruct.getInstance.toEntity(sysRoleDto);
        this.sysRoleService.insert(sysRole);
        SysRoleDto savedRoleDto = SysRoleMapstruct.getInstance.toDto(sysRole);
        return ResponseUtils.result(savedRoleDto);
    }

    @ApiOperation(value = "更新角色")
    @PatchMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> updatge(@RequestBody(required = true) SysRoleDto sysRoleDto){
        SysRole sysRole = SysRoleMapstruct.getInstance.toEntity(sysRoleDto);
        this.sysRoleService.updateById(sysRole);
        SysRoleDto savedRoleDto = SysRoleMapstruct.getInstance.toDto(sysRole);
        return ResponseUtils.result(savedRoleDto);
    }


    @ApiOperation(value = "删除角色")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "角色ID", paramType = "body")
    })
    @DeleteMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@RequestBody List<Long> ids) {
        return ResponseUtils.result(this.sysRoleService.remove(ids));
    }
}
