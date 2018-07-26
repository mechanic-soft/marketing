/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:46
 */
package cn.com.geasy.marketing.api.task;

import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.service.task.RuleService;
import cn.com.geasy.marketing.service.wechat.ChatRecordsService;
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
 * 规则
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Api(tags = "rules", description = "规则接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @Autowired
    private ChatRecordsService chatRecordsService;

    @ApiOperation(value = "规则列表")
    @GetMapping(path = "/rules", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> selectPage(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(required = false) LocalDate createTime,
            @RequestParam(required = true) Integer pageNum,
            @RequestParam(required = false) Integer pageSize){
        RuleDto ruleDto = new RuleDto();
        ruleDto.setCreateTime(createTime);
        return ResponseUtils.result(ruleService.selectDtoPage(pageNum==null?1:pageNum,pageSize==null?10:pageSize,ruleDto));
    }

    @ApiOperation(value = "今日提醒")
    @GetMapping(path = "/rules/remindings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getRemindings(){
        return ResponseUtils.result(this.ruleService.getRemindings());
    }

    @ApiOperation(value = "新建规则")
    @PostMapping(path = "/rules", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(@RequestBody RuleDto ruleDto){
        return ResponseUtils.result(this.ruleService.save(ruleDto));
    }

    @ApiOperation(value = "修改规则")
    @PutMapping(path = "/rules/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> update(@PathVariable Long id){
        RuleDto ruleDto= new RuleDto();
        ruleDto.setId(id);
        return ResponseUtils.result(this.ruleService.update(ruleDto));
    }

    @ApiOperation(value = "获取规则详情")
    @GetMapping(path = "/rules/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getRule(@PathVariable Long id){
        return ResponseUtils.result(this.ruleService.findRuleByRuleId(id));
    }

    /*@ApiOperation(value = "今日提醒跳转加微客户列表")
    @GetMapping(path = "/rules/chatConsumers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getChatConsumersByWxContactIdList(@RequestParam List<Long> customerList,
                                                                      @RequestParam(defaultValue = "1",required = true) int pageNum){
        return ResponseUtils.result(chatRecordsService.getChatConsumersByWxContactIdList(customerList,pageNum));
    }*/
}
