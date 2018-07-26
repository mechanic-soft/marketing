/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午2:38
 */
package cn.com.geasy.marketing.api.task;

import cn.com.geasy.marketing.domain.dto.task.TaskDto;
import cn.com.geasy.marketing.service.task.TaskService;
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

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Api(tags = "tasks", description = "任务接口")
@Slf4j
@RestController
@RequestMapping("/v1")
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "任务列表信息")
    @GetMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> selectPage(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(required = false) LocalDate createTime,
            @RequestParam(required = true) Integer pageNum,
            @RequestParam(required = false) Integer pageSize)
    {
        //localDateToStr(callTimeStart);callTimeStart,callTimeEnd
        TaskDto customerDto = new TaskDto();
        customerDto.setCreateTime(createTime);
        return ResponseUtils.result(taskService.selectDtoPage(pageNum==null?1:pageNum,pageSize==null?10:pageSize,customerDto));
    }

    @ApiOperation(value = "新建任务信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "客户ID", paramType = "body"),
    })
    @PostMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(@RequestBody TaskDto taskDto){
        return ResponseUtils.result(this.taskService.save(taskDto));
    }

    @ApiOperation(value = "修改任务信息")
    @PutMapping(path = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> update(@RequestBody TaskDto taskDto){
        return ResponseUtils.result(this.taskService.update(taskDto));
    }
    @ApiOperation(value = "获取任务详细信息")
    @GetMapping(path = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getTask(@RequestParam Long taskId){
        return ResponseUtils.result(this.taskService.findTaskAndUsersByTaskId(taskId));
    }

    @ApiOperation(value = "获取今日任务信息")
    @GetMapping(path = "/tasks/today", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> dailyTask(){
        return ResponseUtils.result(this.taskService.findDailyTask());
    }

}

