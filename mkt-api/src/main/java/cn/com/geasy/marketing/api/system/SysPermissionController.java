/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/22 下午2:18
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.domain.dto.system.SysPermissionDto;
import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import cn.com.geasy.marketing.mapstruct.system.SysPermissionMapstruct;
import cn.com.geasy.marketing.service.system.SysPermissionService;
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
 * 权限接口
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Permission", description = "权限接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1/sys")
public class SysPermissionController {
    private final SysPermissionService sysPermissionService;

    @Autowired
    public SysPermissionController(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }

    @ApiOperation(value = "获取权限")
    @GetMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getPermissions(@RequestParam(defaultValue = "0") int pageNum){
        return ResponseUtils.result(this.sysPermissionService.findDtoPage(pageNum));
    }

    @ApiOperation(value = "获取匹配权限ID的权限")
    @GetMapping(path = "/permissions/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> get(@PathVariable(required = true) Long id){
        return ResponseUtils.result(this.sysPermissionService.findDtoById(id));
    }

    @ApiOperation(value = "新增权限")
    @PostMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> insert(@RequestBody(required = true) SysPermissionDto sysPermissionDto){
        SysPermission sysPermission = SysPermissionMapstruct.getInstance.toEntity(sysPermissionDto);
        this.sysPermissionService.insert(sysPermission);
        SysPermissionDto savedPermissionDto = SysPermissionMapstruct.getInstance.toDto(sysPermission);
        return ResponseUtils.result(savedPermissionDto);
    }

    @ApiOperation(value = "更新权限")
    @PatchMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> updatge(@RequestBody(required = true) SysPermissionDto sysPermissionDto){
        SysPermission sysPermission = SysPermissionMapstruct.getInstance.toEntity(sysPermissionDto);
        this.sysPermissionService.updateById(sysPermission);
        SysPermissionDto savedPermissionDto = SysPermissionMapstruct.getInstance.toDto(sysPermission);
        return ResponseUtils.result(savedPermissionDto);
    }


    @ApiOperation(value = "删除权限")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "权限ID", paramType = "body")
    })
    @DeleteMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@RequestBody List<Long> ids) {
        return ResponseUtils.result(this.sysPermissionService.remove(ids));
    }
}
