/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 下午2:15
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.domain.dto.system.SysCorpDto;
import cn.com.geasy.marketing.service.system.SysCorpService;
import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.ServiceException;
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
 * 公司API
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "Corp", description = "公司接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1/sys")
public class SysCorpController {
    private final SysCorpService sysCorpService;

    @Autowired
    public SysCorpController(SysCorpService sysCorpService) {
        this.sysCorpService = sysCorpService;
    }

    @ApiOperation(value = "公司列表")
    @GetMapping(path = "/corps", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCorps(@RequestParam(defaultValue = "1") int pageNum) {
        return ResponseUtils.result(this.sysCorpService.selectDtoPage(pageNum));
    }

    @ApiOperation(value = "公司详情")
    @GetMapping(path = "/corps/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCorp(@PathVariable Long id) {
        return ResponseUtils.result(this.sysCorpService.selectDtoById(id));
    }

    @ApiOperation(value = "新增公司")
    @PostMapping(path = "/corps", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> insert(@RequestBody SysCorpDto sysCorp) {
        return ResponseUtils.result(this.sysCorpService.save(sysCorp));
    }

    @ApiOperation(value = "更新公司")
    @PutMapping(path = "/corps", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> update(@RequestBody SysCorpDto sysCorp) {
        if(sysCorp.getId() == null){
            throw new ServiceException(HttpCode.PARAMS_ERROR,"id不能为空");
        }
        return ResponseUtils.result(this.sysCorpService.save(sysCorp));
    }

    @ApiOperation(value = "删除公司")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "公司ID", paramType = "body")
    })
    @DeleteMapping(path = "/corps", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@RequestBody List<Long> ids) {
        return ResponseUtils.result(this.sysCorpService.deleteBatchIds(ids));
    }

}
