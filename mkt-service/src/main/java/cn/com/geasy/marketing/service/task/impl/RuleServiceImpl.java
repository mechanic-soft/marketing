/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.dao.task.RuleMapper;
import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.domain.entity.task.Rule;
import cn.com.geasy.marketing.service.task.RuleService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 规则服务接口实现
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class RuleServiceImpl extends SuperServiceImpl<RuleMapper, Rule> implements RuleService {

    @Override
    public boolean save(RuleDto ruleDto) {
        Rule rule = new Rule();
        rule.setTitle(ruleDto.getTitle());
        rule.setContent(ruleDto.getContent());
        rule.setEndDate(ruleDto.getEndTime());
        rule.setStartDate(ruleDto.getStartTime());
        return false;
    }



}
