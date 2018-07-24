/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:46
 */
package cn.com.geasy.marketing.api.chat;

import cn.com.geasy.marketing.domain.dto.wechat.ChatRecordsDto;
import cn.com.geasy.marketing.service.wechat.ChatRecordsService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
