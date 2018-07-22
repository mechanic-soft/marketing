/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午6:04
 */
package cn.com.geasy.marketing.dao.task;


import cn.com.geasy.marketing.domain.entity.task.RuleTriggerAction;
import com.gitee.mechanic.mybatis.base.SuperMapper;


/**
 * 规则触发行为数据访问接口
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
public interface RuleTriggerActionMapper extends SuperMapper<RuleTriggerAction> {

    //List<Task> findByUserIdPage(@Param("title")String title, @Param("userId") Long userId);

//    List<ArticleHotDto> findByHotArticlePage();

    //List<Article> findBySubscribePage(@Param("userId") Long userId);

    //List<Article> findByLikePage(@Param("userId") Long userId);

//    List<ArticleReadRecordDto> findRecentReadRecord(@Param("userId") Long userId);
    /**
     * 分页查询待办任务信息
     * @param taskDto 查询条件
     * @param pagination 分页信息
     * @return List&lt;TaskDto&gt; 待办任务信息
     */
     //Page<Task> selectAllPage(@Param("td") TaskDto taskDto, Pagination pagination);
}
