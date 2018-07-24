/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:46
 */
package cn.com.geasy.marketing.api.chatSession;

import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.service.task.RuleService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 聊天会话Controller
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Api(tags = "chatSession", description = "聊天会话接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class ChatSessionController {



}
