/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.task.TaskMapper;
import cn.com.geasy.marketing.domain.dto.task.TaskDto;
import cn.com.geasy.marketing.domain.entity.task.Task;
import cn.com.geasy.marketing.domain.entity.task.TaskUser;
import cn.com.geasy.marketing.service.task.TaskService;
import cn.com.geasy.marketing.service.task.TaskUserService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public boolean save(TaskDto taskDto) {
        Long userId = SessionUtils.getUserId();
        boolean returnFlag = false;
        try {
            List<TaskUser> taskUserList = new ArrayList<TaskUser>();
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setContent(taskDto.getContent());
            task.setCreateUser(userId);
            //保存到task表
            this.baseMapper.insert(task);
            Long taskId = task.getId();
            taskDto.getUserId().forEach(i -> {
                TaskUser taskUser = new TaskUser();
                taskUser.setUserId(i);
                taskUser.setTaskId(taskId);
                taskUser.setCreateUser(userId);
                taskUser.setUpdateUser(userId);
                taskUserList.add(taskUser);
            });
            //保存到taskUser表
            returnFlag = taskUserService.insertOrUpdateAllColumnBatch(taskUserList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnFlag;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public boolean update(TaskDto taskDto) {
        Long newUserId = SessionUtils.getUserId();
        try {
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setContent(taskDto.getContent());
            if (taskDto.getId() !=null ){
                task.setId(taskDto.getId());
            }else{
                return false;
            }
            ArrayList<TaskUser> updateTaskUserList = new ArrayList<TaskUser>();
            ArrayList<TaskUser> addTaskUserList = new ArrayList<TaskUser>();
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
                        taskUser.setCreateUser(newUserId);
                        taskUser.setUpdateUser(newUserId);
                        addTaskUserList.add(taskUser);
                    }
                }
            });
            //old 1,2,3      1,2  需要处理3的状态为删除   3是有id
            oldTaskUserMap.forEach((key,value)->{
                TaskUser oldTaskUser =(TaskUser) value;
                oldTaskUser.setStatus(0);
                oldTaskUser.setUpdateUser(newUserId);
                updateTaskUserList.add(oldTaskUser);
            });
            //保存task表
            super.insertOrUpdate(task);
            //保存任务用户表rele_rule_user
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
        columnMap.put("status", Const.ONE);
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
        Long userId = SessionUtils.getUserId();
        List<TaskUser> dailyTaskList = taskUserService.findDailyTask(userId);
        TaskUser taskUser = null;
        TaskDto returnTaskDto = new TaskDto();
        if(dailyTaskList.size() > 0){
            taskUser = dailyTaskList.get(0);
        }else{
            return returnTaskDto;
        }
        Long taskId = taskUser.getTaskId();
        Task task = this.selectById(taskId);

        if(task != null){
            returnTaskDto.setTitle(task.getTitle());
            returnTaskDto.setContent(task.getContent());
        }
        return returnTaskDto;
    }

    @Override
    public Page<TaskDto> selectDtoPage(int pageNum, int pageSize, TaskDto taskDto) {
        Page<TaskDto> page = new Page<>(pageNum,pageSize);
        List<TaskDto> taskDtoList = baseMapper.selectDtoPage(page, taskDto);
        page.setRecords(taskDtoList);
        return page;
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
