/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午6:04
 */
package cn.com.geasy.marketing.dao.task;

import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.domain.dto.task.TaskDto;
import cn.com.geasy.marketing.domain.entity.task.Rule;
import cn.com.geasy.marketing.domain.entity.task.Task;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规则数据访问接口
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
public interface RuleMapper extends SuperMapper<Rule> {
    //List<Task> findByUserIdPage(@Param("title")String title, @Param("userId") Long userId);

//    List<ArticleHotDto> findByHotArticlePage();

    //List<Article> findBySubscribePage(@Param("userId") Long userId);

    //List<Article> findByLikePage(@Param("userId") Long userId);

//    List<ArticleReadRecordDto> findRecentReadRecord(@Param("userId") Long userId);
    /**
     * 分页查询待办任务信息
     * @param ruleDto 查询条件
     * @param page 分页对象
     * @return List&lt;RuleDto&gt; 待办任务信息
     */
    List<RuleDto> selectDtoPage(Page page , @Param("ruleDto") RuleDto ruleDto);
}
