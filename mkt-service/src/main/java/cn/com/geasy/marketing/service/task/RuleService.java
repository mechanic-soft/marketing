/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午8:02
 */
package cn.com.geasy.marketing.service.task;


import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxCustomerDto;
import cn.com.geasy.marketing.domain.entity.task.Rule;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 规则服务接口
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
public interface RuleService extends SuperService<Rule> {
    /**
     * 保存规则信息
     *
     * @param ruleDto
     * @return boolean
     */
    boolean save(RuleDto ruleDto);

    /**
     * 修改任务信息
     *
     * @param ruleDto
     * @return boolean
     */
    boolean update(RuleDto ruleDto);

    /**
     * 获取今日提醒
     *
     * @return RuleDto
     */
    RuleDto getRemindings();

    /**
     * 获取规则详情
     * @param ruleId
     * @return RuleDto
     */
    RuleDto findRuleByRuleId(Long ruleId);

    /**
     * 返回规则的分页列表
     *
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param ruleDto  条件对象
     * @return Page&lt;RuleDto&gt; 待办任务分页列表
     */
    Page<RuleDto> selectDtoPage(int pageNum, int pageSize, RuleDto ruleDto);
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
