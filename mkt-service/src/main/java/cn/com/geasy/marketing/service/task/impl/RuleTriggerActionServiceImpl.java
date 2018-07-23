/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.dao.task.RuleCustomerLabelMapper;
import cn.com.geasy.marketing.dao.task.RuleTriggerActionMapper;
import cn.com.geasy.marketing.domain.entity.task.RuleCustomerLabel;
import cn.com.geasy.marketing.domain.entity.task.RuleTriggerAction;
import cn.com.geasy.marketing.service.task.RuleCustomerLabelService;
import cn.com.geasy.marketing.service.task.RuleTriggerActionService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 规则触发行为接口实现
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class RuleTriggerActionServiceImpl extends SuperServiceImpl<RuleTriggerActionMapper, RuleTriggerAction> implements RuleTriggerActionService {


}
