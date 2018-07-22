/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午8:02
 */
package cn.com.geasy.marketing.service.task;


import cn.com.geasy.marketing.domain.dto.task.TaskDto;
import cn.com.geasy.marketing.domain.entity.task.Task;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;


import java.util.List;

/**
 * 待办任务服务接口
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
public interface TaskService extends SuperService<Task> {
    /**
     * 保存任务信息
     *
     * @param taskDto
     * @return boolean
     */
    boolean save(TaskDto taskDto);

    /**
     * 修改任务信息
     *
     * @param taskDto
     * @return boolean
     */
    boolean update(TaskDto taskDto);

    /**
     * 查找任务详细信息
     *
     * @param taskId
     * @return TaskDto
     */
    TaskDto findTaskAndUsersByTaskId(Long taskId);

    /**
     * 获取今日任务信息
     * @return TaskDto
     */
    TaskDto findDailyTask();
    /**
     * 返回所有待办任务的分页列表
     *
     * @param pageNum 页码
     * @return Page&lt;TaskDto&gt; 待办任务分页列表
     */
    //Page<TaskDto> selectDtoPage(int pageNum);
    //Article saveArticle(Article article, Long wechatUserId);

    //Page<ArticleListDto> findByUserIdPage(Page page, String title, Long currentMpUserId);

    //Page<ArticleDto> findById(Long id);
}
