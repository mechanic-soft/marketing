/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:46
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.mapstruct.system.SysUserMapstruct;
import cn.com.geasy.marketing.service.system.SysUserService;
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
 * 系统用户API
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "User", description = "用户接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1/sys")
public class SysUserController {

    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @ApiOperation(value = "获取用户")
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getUsers(@RequestParam (required = false, defaultValue = "0") int pageNum){

        return ResponseUtils.result(this.sysUserService.findPage(pageNum));
    }

    @ApiOperation(value = "获取匹配用户ID的用户")
    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getUser(@PathVariable(required = true) Long id){
        SysUserDto sysUserDto = SysUserMapstruct.getInstance.toDto(this.sysUserService.selectById(id));
        return ResponseUtils.result(sysUserDto);
    }

    @ApiOperation(value = "新增用户")
    @PostMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(@RequestBody(required = true) SysUserDto sysUserDto){
        sysUserDto.setId(null);
        this.sysUserService.insertOrUpdate(sysUserDto);
        return ResponseUtils.result(sysUserDto);
    }
    
    @ApiOperation(value = "更新用户")
    @PatchMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> updatge(@RequestBody(required = true) SysUserDto sysUserDto){
        this.sysUserService.insertOrUpdate(sysUserDto);
        return ResponseUtils.result(sysUserDto);
    }
    
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "公司ID", paramType = "body")
    })
    @DeleteMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@RequestBody List<Long> ids) {
        return ResponseUtils.result(this.sysUserService.remove(ids));
    }
}
