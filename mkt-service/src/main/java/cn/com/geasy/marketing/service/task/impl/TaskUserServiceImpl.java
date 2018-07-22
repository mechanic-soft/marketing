/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;



import cn.com.geasy.marketing.dao.task.TaskUserMapper;
import cn.com.geasy.marketing.domain.entity.task.TaskUser;
import cn.com.geasy.marketing.service.task.TaskUserService;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户任务服务接口实现
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class TaskUserServiceImpl extends SuperServiceImpl<TaskUserMapper, TaskUser> implements TaskUserService {

    @Override
    public List<TaskUser> findTaskUserByTaskId(Long taskId) {
        HashMap<String,Object> columnMap = new HashMap<String,Object>(2);
        columnMap.put("task_id",taskId);
       return super.selectByMap(columnMap);
    }

    @Override
    public List<TaskUser> findDailyTask(Long userId) {
       return baseMapper.findDailyTaskByUserId(userId);
    }
}
