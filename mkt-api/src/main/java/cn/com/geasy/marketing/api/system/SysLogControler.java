/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 下午9:24
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.domain.dto.system.SysLogDto;
import cn.com.geasy.marketing.domain.entity.system.SysLog;
import cn.com.geasy.marketing.mapstruct.system.SysLogMapstruct;
import cn.com.geasy.marketing.service.system.SysLogService;
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
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Log", description = "菜单接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1/sys")
public class SysLogControler {

    private final SysLogService sysLogService;

    @Autowired
    public SysLogControler(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @ApiOperation(value = "日志列表")
    @GetMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getLogs(@RequestParam(defaultValue = "0") int pageNum) {
        return ResponseUtils.result(this.sysLogService.selectDtoPage(pageNum));
    }

    @ApiOperation(value = "日志详情")
    @GetMapping(path = "/logs/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getLog(@PathVariable Long id) {
        return ResponseUtils.result(this.sysLogService.selectDtoById(id));
    }

    @ApiOperation(value = "新增日志")
    @PostMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> insert(@RequestBody SysLogDto sysLogDto) {
        SysLog SysLog = SysLogMapstruct.getInstance.toEntity(sysLogDto);
        this.sysLogService.insert(SysLog);
        SysLogDto savedRoleDto = SysLogMapstruct.getInstance.toDto(SysLog);
        return ResponseUtils.result(savedRoleDto);
    }

    @ApiOperation(value = "更新日志")
    @PutMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> update(@RequestBody SysLogDto sysLogDto) {
        SysLog SysLog = SysLogMapstruct.getInstance.toEntity(sysLogDto);
        this.sysLogService.updateById(SysLog);
        SysLogDto savedRoleDto = SysLogMapstruct.getInstance.toDto(SysLog);
        return ResponseUtils.result(savedRoleDto);
    }

    @ApiOperation(value = "删除日志")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "日志ID", paramType = "body")
    })
    @DeleteMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@RequestBody List<Long> ids) {
        return ResponseUtils.result(this.sysLogService.deleteBatchIds(ids));
    }
}
