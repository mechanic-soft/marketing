/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.dao.task.RuleMapper;
import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.domain.entity.task.Rule;
import cn.com.geasy.marketing.domain.entity.task.RuleCustomerLabel;
import cn.com.geasy.marketing.domain.entity.task.RuleTriggerAction;
import cn.com.geasy.marketing.service.task.RuleCustomerLabelService;
import cn.com.geasy.marketing.service.task.RuleService;
import cn.com.geasy.marketing.service.task.RuleTriggerActionService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 规则服务接口实现
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class RuleServiceImpl extends SuperServiceImpl<RuleMapper, Rule> implements RuleService {

    @Autowired
    private RuleCustomerLabelService ruleCustomerLabelService;

    @Autowired
    private RuleTriggerActionService ruleTriggerActionService;

    @Override
    public boolean save(RuleDto ruleDto) {
        Long userId = SessionUtils.getUserId();
        Rule rule = new Rule();
        rule.setTitle(ruleDto.getTitle());
        rule.setContent(ruleDto.getContent());
        rule.setEndDate(ruleDto.getEndTime());
        rule.setStartDate(ruleDto.getStartTime());
        rule.setCreateUser(userId);
        rule.setUpdateUser(userId);
        super.insertOrUpdateAllColumn(rule);
        Long ruleId = rule.getId();
        List<Long> customerTagsList = ruleDto.getCustomerTags();
        List<RuleCustomerLabel> addRuleCustomerLabelList = new ArrayList<RuleCustomerLabel>();
        customerTagsList.forEach(customerTagId ->{
            RuleCustomerLabel ruleCustomerLabel = new RuleCustomerLabel();
            ruleCustomerLabel.setRuleId(ruleId);
            ruleCustomerLabel.setTagId(customerTagId);
            addRuleCustomerLabelList.add(ruleCustomerLabel);
        });
        List<RuleTriggerAction> triggersList = ruleDto.getTriggers();
        List<RuleTriggerAction> addRuleTriggerActionList = new ArrayList<RuleTriggerAction>();
        triggersList.forEach( ruleTriggerActionObj ->{
            RuleTriggerAction ruleTriggerAction = new RuleTriggerAction();
            ruleTriggerAction.setRuleId(ruleId);
            ruleTriggerAction.setAction(ruleTriggerActionObj.getAction());
            ruleTriggerAction.setCondition(ruleTriggerActionObj.getCondition());
            ruleTriggerAction.setFrequency(ruleTriggerActionObj.getFrequency());
            addRuleTriggerActionList.add(ruleTriggerAction);
        });
        if(addRuleCustomerLabelList.size() > 0){
            ruleCustomerLabelService.insertOrUpdateAllColumnBatch(addRuleCustomerLabelList);
        }
        if (addRuleTriggerActionList.size() > 0 ){
            ruleTriggerActionService.insertOrUpdateAllColumnBatch(addRuleTriggerActionList);
        }
        return true;
    }

    @Override
    public boolean update(RuleDto ruleDto) {
        Long ruleId = ruleDto.getId();
        Rule rule = new Rule();
        rule.setTitle(ruleDto.getTitle());
        rule.setContent(ruleDto.getContent());
        rule.setStartDate(ruleDto.getStartTime());
        rule.setEndDate(ruleDto.getEndTime());
        //根据规则主键ruleId，去查找规则客户标签关联表
        HashMap<String,Object> columnMap = new HashMap<String,Object>(5);
        columnMap.put("rule_id",ruleId);
        List<RuleCustomerLabel> oldRuleCustomerLabelList = ruleCustomerLabelService.selectByMap(columnMap);
        ArrayList<RuleCustomerLabel> updateRuleCustomerLabelList = new ArrayList<RuleCustomerLabel>();
        ArrayList<RuleCustomerLabel> addRuleCustomerLabelList = new ArrayList<RuleCustomerLabel>();
        HashMap<String,Object> oldRuleCustomerLabelMap = new HashMap<String,Object>();
        oldRuleCustomerLabelList.forEach(ruleCustomerLabelObj ->{
            oldRuleCustomerLabelMap.put(ruleCustomerLabelObj.getRuleId().toString() +"@"+ ruleCustomerLabelObj.getTagId().toString(),ruleCustomerLabelObj);
        });
        ruleDto.getCustomerTags().forEach( customerTagId -> {
            String ruleIdAndCustomerTagId = ruleId.toString()+"@"+customerTagId.toString();
            if(oldRuleCustomerLabelMap.containsKey(ruleIdAndCustomerTagId)){
                oldRuleCustomerLabelMap.remove(ruleIdAndCustomerTagId);
            }else{
                RuleCustomerLabel oldRuleCustomerLabel = (RuleCustomerLabel)oldRuleCustomerLabelMap.get(ruleIdAndCustomerTagId);
                if(oldRuleCustomerLabel ==null){
                    RuleCustomerLabel ruleCustomerLabelObj = new RuleCustomerLabel();
                    ruleCustomerLabelObj.setRuleId(ruleId);
                    ruleCustomerLabelObj.setTagId(customerTagId);
                    addRuleCustomerLabelList.add(ruleCustomerLabelObj);
                }
            }
        });
        oldRuleCustomerLabelMap.forEach((key,value)->{
            RuleCustomerLabel oldRuleCustomerLabel =(RuleCustomerLabel) value;
            oldRuleCustomerLabel.setStatus(0);
            updateRuleCustomerLabelList.add(oldRuleCustomerLabel);
        });
        //根据规则主键ruleId，去查找规则触发行为表
        HashMap<String,Object> columnRuleTriggerActionMap = new HashMap<String,Object>(5);
        columnRuleTriggerActionMap.put("rule_id",ruleId);
        List<RuleTriggerAction> oldRuleTriggerActionList = ruleTriggerActionService.selectByMap(columnMap);
        ArrayList<RuleTriggerAction> updateRuleTriggerActionList = new ArrayList<RuleTriggerAction>();
        ArrayList<RuleTriggerAction> addRuleTriggerActionList = new ArrayList<RuleTriggerAction>();
        HashMap<String,Object> oldRuleTriggerActionMap = new HashMap<String,Object>();
        oldRuleTriggerActionList.forEach(ruleTriggerActionObj ->{
            oldRuleTriggerActionMap.put(ruleTriggerActionObj.getRuleId().toString() +"@"+ ruleTriggerActionObj.getAction().toString(),ruleTriggerActionObj);
        });
        ruleDto.getTriggers().forEach( ruleTriggerActionObj -> {
            Integer actionId = ruleTriggerActionObj.getAction();
            String ruleIdAndActionId = ruleId.toString()+"@"+actionId.toString();
            if(oldRuleTriggerActionMap.containsKey(ruleIdAndActionId)){
                oldRuleTriggerActionMap.remove(ruleIdAndActionId);
            }else{
                RuleTriggerAction oldRuleTriggerAction = (RuleTriggerAction)oldRuleTriggerActionMap.get(ruleIdAndActionId);
                if(oldRuleTriggerAction ==null){
                    RuleTriggerAction newRuleTriggerActionObj = new RuleTriggerAction();
                    newRuleTriggerActionObj.setRuleId(ruleId);
                    newRuleTriggerActionObj.setAction(actionId);
                    addRuleTriggerActionList.add(newRuleTriggerActionObj);
                }
            }
        });
        oldRuleTriggerActionMap.forEach((key,value)->{
            RuleTriggerAction oldRuleTriggerAction =(RuleTriggerAction) value;
            oldRuleTriggerAction.setStatus(0);
            updateRuleTriggerActionList.add(oldRuleTriggerAction);
        });
        //保存规则表
        super.insertOrUpdate(rule);
        //保存规则客户标签关联表
        if(addRuleCustomerLabelList.size() > 0 ){
            ruleCustomerLabelService.insertOrUpdateAllColumnBatch(addRuleCustomerLabelList);
        }
        if(updateRuleCustomerLabelList.size() > 0 ){
            ruleCustomerLabelService.insertOrUpdateAllColumnBatch(updateRuleCustomerLabelList);
        }
        //保存规则触发行为表
        if(addRuleTriggerActionList.size() > 0 ){
            ruleTriggerActionService.insertOrUpdateAllColumnBatch(addRuleTriggerActionList);
        }
        if(updateRuleTriggerActionList.size() > 0 ){
            ruleTriggerActionService.insertOrUpdateAllColumnBatch(updateRuleTriggerActionList);
        }
        return true;
    }

    @Override
    public RuleDto getRemindings() {
        Long userId = SessionUtils.getUserId();
        //根据用户去获取
        return null;
    }

    @Override
    public RuleDto findRuleByRuleId(Long ruleId) {
        Long userId = SessionUtils.getUserId();
        HashMap<String,Object> columnRuleMap = new HashMap<String,Object>(3);
        columnRuleMap.put("id",ruleId);
        columnRuleMap.put("status",1);
        List<Rule> ruleList = super.selectByMap(columnRuleMap);
        Rule rule = null;
        RuleDto returnRuleDto = new RuleDto();
        if(ruleList.size() > 0 ){
            rule = ruleList.get(0);
        }else{
            return returnRuleDto;
        }
        HashMap<String,Object> columnRuleCustomerLabelMap = new HashMap<String,Object>(3);
        columnRuleCustomerLabelMap.put("rule_id",ruleId);
        //0代表状态为删除，1代表状态为正常
        columnRuleCustomerLabelMap.put("status",1);
        List<RuleCustomerLabel> ruleCustomerLabelList = ruleCustomerLabelService.selectByMap(columnRuleCustomerLabelMap);
        ArrayList<Long> customerTagsList = new ArrayList<Long>();
        ruleCustomerLabelList.forEach( ruleCustomerLabelObj -> {
            Long tagId = ruleCustomerLabelObj.getTagId();
            customerTagsList.add(tagId);
        });
        HashMap<String,Object> columnRuleTriggerActionMap = new HashMap<String,Object>(3);
        columnRuleTriggerActionMap.put("rule_id",ruleId);
        //0代表状态为删除，1代表状态为正常
        columnRuleTriggerActionMap.put("status",1);
        List<RuleTriggerAction> ruleTriggerActionList = ruleTriggerActionService.selectByMap(columnRuleTriggerActionMap);
        returnRuleDto.setId(ruleId);
        returnRuleDto.setContent(rule.getContent());
        returnRuleDto.setTitle(rule.getTitle());
        returnRuleDto.setStartTime(rule.getStartDate());
        returnRuleDto.setEndTime(rule.getEndDate());
        returnRuleDto.setCustomerTags(customerTagsList);
        returnRuleDto.setTriggers(ruleTriggerActionList);
        return returnRuleDto;
    }


}
