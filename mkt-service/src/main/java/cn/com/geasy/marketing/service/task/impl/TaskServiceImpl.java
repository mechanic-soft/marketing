/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.dao.task.TaskMapper;
import cn.com.geasy.marketing.domain.dto.task.TaskDto;
import cn.com.geasy.marketing.domain.entity.task.Task;
import cn.com.geasy.marketing.domain.entity.task.TaskUser;
import cn.com.geasy.marketing.mapstruct.task.TaskMapstruct;
import cn.com.geasy.marketing.service.task.TaskService;
import cn.com.geasy.marketing.service.task.TaskUserService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;

import org.h2.mvstore.type.ObjectDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 待办任务服务接口实现
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class TaskServiceImpl extends SuperServiceImpl<TaskMapper, Task> implements TaskService {
    @Autowired
    private TaskUserService taskUserService;
    @Override
    public boolean save(TaskDto taskDto) {
        List<TaskUser> taskUserList = new ArrayList<TaskUser>();
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setContent(taskDto.getContent());
        //TODO 判断新建的任务重复问题
        //保存到task表
        super.insertOrUpdate(task);
        Long taskId = task.getId();
        taskDto.getUserId().forEach(i -> {
            TaskUser taskUser = new TaskUser();
            taskUser.setUserId(i);
            taskUser.setTaskId(taskId);
            taskUserList.add(taskUser);
        });
        //保存到taskUser表
        return taskUserService.insertOrUpdateBatch(taskUserList);
    }

    @Override
    public boolean update(TaskDto taskDto) {
        try {
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setContent(taskDto.getContent());
            if (taskDto.getId() !=null ){
                task.setId(taskDto.getId());
            }
            super.insertOrUpdateAllColumn(task);
            List<TaskUser> updateTaskUserList = new ArrayList<TaskUser>();
            List<TaskUser> addTaskUserList = new ArrayList<TaskUser>();
            HashMap<String,Object> oldTaskUserMap = new HashMap<String,Object>();
            //根据任务的id，获取到之前的用户任务信息。
            List<TaskUser> taskUserByTaskIdList = taskUserService.findTaskUserByTaskId(task.getId());
            taskUserByTaskIdList.forEach(taskObj ->{
                oldTaskUserMap.put(taskObj.getTaskId().toString() +"@"+ taskObj.getUserId().toString(),taskObj);
            });
            taskDto.getUserId().forEach(userId -> {
                if(oldTaskUserMap.containsKey(task.getId().toString()+"@"+userId.toString())){
                    oldTaskUserMap.remove(task.getId().toString()+"@"+userId.toString());
                }else{
                    //old 1,2  1,2,3  需要处理3的记录添加         3是没有id
                    TaskUser oldTaskUser = (TaskUser)oldTaskUserMap.get(task.getId().toString() + "@" + userId.toString());
                    if(oldTaskUser ==null){
                        TaskUser taskUser = new TaskUser();
                        taskUser.setUserId(userId);
                        taskUser.setTaskId(task.getId());
                        addTaskUserList.add(taskUser);
                    }
                }
            });
            //old 1,2,3      1,2  需要处理3的状态为删除   3是有id
            oldTaskUserMap.forEach((key,value)->{
                TaskUser oldTaskUser =(TaskUser) value;
                oldTaskUser.setStatus(0);
                updateTaskUserList.add(oldTaskUser);
            });
            if(addTaskUserList.size() >0){
                taskUserService.insertOrUpdateAllColumnBatch(addTaskUserList);
            }
            if(updateTaskUserList.size() >0){
                taskUserService.insertOrUpdateAllColumnBatch(updateTaskUserList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public TaskDto findTaskAndUsersByTaskId(Long taskId) {
        HashMap<String,Object> columnMap = new HashMap<String,Object>(5);
        columnMap.put("id",taskId);
        //0 是删除，1 是正常
        columnMap.put("status",1);
        List<Task> tasks = super.selectByMap(columnMap);
        TaskDto returnTaskDto = new TaskDto();
        if(tasks.size() <= 0){
            return returnTaskDto;
        }
        List<TaskUser> taskUserByTaskId = taskUserService.findTaskUserByTaskId(taskId);
        List<Long> returnUserIdList = new ArrayList<Long>();
        taskUserByTaskId.forEach(taskUser -> {
            if(taskUser.getStatus() != 0){
                returnUserIdList.add(taskUser.getUserId());
            }
        });
        returnTaskDto.setId(tasks.get(0).getId());
        returnTaskDto.setTitle(tasks.get(0).getTitle());
        returnTaskDto.setContent(tasks.get(0).getContent());
        returnTaskDto.setUserId(returnUserIdList);
        return returnTaskDto;
    }

    @Override
    public TaskDto findDailyTask() {
        //Long userId = SessionUtils.getUserId();
        Long userId  = 101L;
        List<TaskUser> dailyTaskList = taskUserService.findDailyTask(userId);
        TaskUser taskUser = null;
        if(dailyTaskList.size() > 0){
            taskUser = dailyTaskList.get(0);
        }
        Long taskId = taskUser.getTaskId();
        Task task = this.selectById(taskId);
        TaskDto returnTaskDto = new TaskDto();
        if(task != null){
            returnTaskDto.setTitle(task.getTitle());
            returnTaskDto.setContent(task.getContent());
        }
        return returnTaskDto;
    }

    /*@Override
    public Page<TaskDto> selectDtoPage(int pageNum) {
        Page<Task> page = PageUtils.getPage(pageNum);
        page = super.selectPage(page);
        List<TaskDto> corpDtos = TaskMapstruct.getInstance.toDtoList(page.getRecords());
        return PageUtils.getPage(page, corpDtos);
    }*/
   /* @Override
    public List<Task> findTagByArticleId(Long articleId) {
        return baseMapper.findTagByArticleId(articleId);
    }*/
}
