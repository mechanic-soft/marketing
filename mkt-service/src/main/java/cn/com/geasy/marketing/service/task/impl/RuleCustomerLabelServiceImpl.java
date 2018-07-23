/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.dao.task.RuleCustomerLabelMapper;
import cn.com.geasy.marketing.dao.task.TaskUserMapper;
import cn.com.geasy.marketing.domain.entity.task.RuleCustomerLabel;
import cn.com.geasy.marketing.service.task.RuleCustomerLabelService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 规则客户标签关联接口实现
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class RuleCustomerLabelServiceImpl extends SuperServiceImpl<RuleCustomerLabelMapper, RuleCustomerLabel> implements RuleCustomerLabelService {


}
