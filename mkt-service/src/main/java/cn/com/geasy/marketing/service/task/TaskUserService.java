/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午8:02
 */
package cn.com.geasy.marketing.service.task;


import cn.com.geasy.marketing.domain.dto.task.TaskDto;
import cn.com.geasy.marketing.domain.entity.task.Task;
import cn.com.geasy.marketing.domain.entity.task.TaskUser;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 用户任务服务接口
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
public interface TaskUserService extends SuperService<TaskUser> {
    /***
     * 根据任务主键获取用户任务
     * @param taskId
     * @return
     */
    List<TaskUser> findTaskUserByTaskId(Long taskId);

    /***
     * 获取今日任务
     * @param userId
     * @return List<TaskUser>
     */
    List<TaskUser> findDailyTask(Long userId);

    /**
     * 保存任务信息
     *
     * @param title 标题信息
     * @param content 内容信息
     * @param userIds 用户id信息
     * @return boolean
     */
    //boolean save(String title, String content, List<Long> userIds);

    /**
     * 修改任务信息
     *
     * @param id 任务主键
     * @param title 标题信息
     * @param content 内容信息
     * @param userIds 用户id信息
     * @return boolean
     */
    //boolean update(Long id, String title, String content, List<Integer> userIds);
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
