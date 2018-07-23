/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午2:38
 */
package cn.com.geasy.marketing.api.task;

import cn.com.geasy.marketing.service.task.TaskUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户任务
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Api(tags = "task", description = "用户任务接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class TaskUserController {

    private final TaskUserService taskUserService;
    @Autowired
    public TaskUserController(TaskUserService taskUserService) {
        this.taskUserService = taskUserService;
    }

/*    @ApiOperation(value = "任务列表信息")
    @GetMapping(path = "/task", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getTasks(@RequestParam(defaultValue = "0") int pageNume){
        return ResponseUtils.result(this.taskService.selectDtoPage(pageNume));
    }

    @ApiOperation(value = "新建任务信息")
    @PostMapping(path = "/task", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(String title, String content, List<Long> userIds){
        return ResponseUtils.result(this.taskService.save(title,content,userIds));
    }

    @ApiOperation(value = "修改任务信息")
    @PutMapping(path = "/task/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> update(Long id,String title, String content, List<Integer> userIds){
        return ResponseUtils.result(this.taskService.update(id,title,content,userIds));
    }
    @ApiOperation(value = "获取任务详细信息")
    @GetMapping(path = "/task/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getTask(@PathVariable Long id){
        return ResponseUtils.result("");
    }*/
}
