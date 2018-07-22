/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 下午2:15
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.domain.dto.system.SysCorpDto;
import cn.com.geasy.marketing.service.system.SysCorpService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 请在此写下该类的说明
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

    @ApiOperation(value = "获取公司信息")
    @GetMapping(path = "/corps", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCorps(@RequestParam(defaultValue = "0") int pageNum,
                                             @RequestParam List<Long> ids,
                                             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam() LocalDate callTime) {
        return ResponseUtils.result(this.sysCorpService.selectDtoPage(pageNum));
    }

    @ApiOperation(value = "获取匹配ID的公司信息")
    @GetMapping(path = "/corps/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCorp(@PathVariable Long id) {
        return ResponseUtils.result(this.sysCorpService.selectDtoById(id));
    }

    @ApiOperation(value = "保存公司信息")
    @PostMapping(path = "/corps", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(@RequestBody SysCorpDto sysCorp) {
        return ResponseUtils.result(this.sysCorpService.save(sysCorp));
    }

    @ApiOperation(value = "删除公司信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "公司ID", paramType = "body")
    })
    @DeleteMapping(path = "/corps", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> delete(@RequestBody List<Long> ids) {
        return ResponseUtils.result(this.sysCorpService.deleteBatchIds(ids));
    }

}
