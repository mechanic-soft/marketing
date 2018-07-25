/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:46
 */
package cn.com.geasy.marketing.api.chat;

import cn.com.geasy.marketing.domain.dto.wechat.ChatRecordsDto;
import cn.com.geasy.marketing.service.wechat.ChatRecordsService;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 会话Controller
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Api(tags = "chat", description = "会话接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class ChatController {

    @Autowired
    private ChatRecordsService chatRecordsService;

    @ApiOperation(value = "添加聊天记录")
    @PostMapping(path = "/chatRecords", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(@RequestBody ChatRecordsDto chatRecordsDto){
        return ResponseUtils.result(chatRecordsService.save(chatRecordsDto));
    }

    @ApiOperation(value = "聊天记录")
    @GetMapping(path = "/chatRecords", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> findChatRecords(@RequestParam String username, @RequestParam(required = false) String keyword,@RequestParam(defaultValue = "1",required = false) Integer pageNum){
        return ResponseUtils.result(chatRecordsService.findChatRecordsByCondition(username,keyword,pageNum));
    }

    @ApiOperation(value = "加微客户列表")
    @GetMapping(path = "/chatConsumers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> findChatConsumersList(@RequestParam(defaultValue = "0",required = false) Integer isSort,@RequestParam(defaultValue = "1",required = false) int pageNum){
        return ResponseUtils.result(chatRecordsService.getChatConsumersList(isSort,pageNum));
    }


}
