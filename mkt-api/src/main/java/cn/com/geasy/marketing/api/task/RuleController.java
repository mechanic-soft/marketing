/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:46
 */
package cn.com.geasy.marketing.api.task;

import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.service.system.SysUserService;
import cn.com.geasy.marketing.service.task.RuleService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 规则
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Api(tags = "rule", description = "规则接口")
@Slf4j
@RestController
public class RuleController {

    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @ApiOperation(value = "新建规则")
    @PutMapping(path = "/rules", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(@RequestBody RuleDto ruleDto){
        return ResponseUtils.result(this.save(ruleDto));
    }
/*
    @ApiOperation(value = "获取匹配用户ID的用户")
    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getUser(@PathVariable(required = true) Long id){
        SysUserDto sysUserDto = SysUserMapstruct.getInstance.toDto(this.sysUserService.selectById(id));
        return ResponseUtils.result(sysUserDto);
    }*/
}
