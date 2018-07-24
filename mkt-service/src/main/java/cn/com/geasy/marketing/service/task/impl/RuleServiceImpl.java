/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.task.RuleMapper;
import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.entity.article.ArticleRead;
import cn.com.geasy.marketing.domain.entity.article.ArticleSubscription;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.customer.ReleCustomerTag;
import cn.com.geasy.marketing.domain.entity.task.Rule;
import cn.com.geasy.marketing.domain.entity.task.RuleCustomerLabel;
import cn.com.geasy.marketing.domain.entity.task.RuleTriggerAction;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.mapstruct.wechat.WxContactMapstruct;
import cn.com.geasy.marketing.service.article.ArticleReadService;
import cn.com.geasy.marketing.service.article.ArticleSubscriptionService;
import cn.com.geasy.marketing.service.customer.CustomerService;
import cn.com.geasy.marketing.service.customer.ReleCustomerTagService;
import cn.com.geasy.marketing.service.task.RuleCustomerLabelService;
import cn.com.geasy.marketing.service.task.RuleService;
import cn.com.geasy.marketing.service.task.RuleTriggerActionService;
import cn.com.geasy.marketing.service.wechat.ChatRecordsService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 规则服务接口实现
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class RuleServiceImpl extends SuperServiceImpl<RuleMapper, Rule> implements RuleService
{

    @Autowired
    private RuleCustomerLabelService ruleCustomerLabelService;

    @Autowired
    private RuleTriggerActionService ruleTriggerActionService;

    @Autowired
    private ReleCustomerTagService releCustomerTagService;

    @Autowired
    private ChatRecordsService chatRecordsService;

    @Autowired
    private ArticleReadService articleReadService;

    @Autowired
    private ArticleSubscriptionService articleSubscriptionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WxContactService wxContactService;

    @Override
    public RuleDto getRemindings() {
        Long userId = SessionUtils.getUserId();
        LocalDate newLocalDate = LocalDate.now();
        EntityWrapper<Rule> ew=new EntityWrapper<Rule>();
        ew.where("status = {0}",Const.ONE);
        ew.orderBy("end_date",false);
        List<Rule> ruleList = this.selectList(ew);
        RuleDto returnRuleDto = new RuleDto();
        List<RuleCustomerLabel> returnRuleCustomerLabelList = new ArrayList<RuleCustomerLabel>();
        List<RuleTriggerAction> returnRuleTriggerActionList = new ArrayList<RuleTriggerAction>();
        if(ruleList.size() > 0 ){
            Rule rule = ruleList.get(0);
            Long ruleId = rule.getId();
            LocalDate endDate = rule.getEndDate();
            if(newLocalDate.isBefore(endDate) || newLocalDate.isEqual(endDate)){
                HashMap<String,Object> columnRuleCustomerLabelMap = new HashMap<String,Object>(2);
                columnRuleCustomerLabelMap.put("rule_id",ruleId);
                columnRuleCustomerLabelMap.put("status",Const.ONE);
                returnRuleCustomerLabelList = ruleCustomerLabelService.selectByMap(columnRuleCustomerLabelMap);

                HashMap<String,Object> columnRuleTriggerActionMap = new HashMap<String,Object>(2);
                columnRuleTriggerActionMap.put("rule_id",ruleId);
                columnRuleTriggerActionMap.put("status", Const.ONE);
                returnRuleTriggerActionList = ruleTriggerActionService.selectByMap(columnRuleTriggerActionMap);

                //组合条件之标签条件
                List<Long> tagList = new ArrayList<Long>();
                for (int i = 0; i < returnRuleCustomerLabelList.size(); i++) {
                    RuleCustomerLabel ruleCustomerLabel = returnRuleCustomerLabelList.get(i);
                    tagList.add(ruleCustomerLabel.getTagId());
                }
                //根据标签主键关联客户标签关联表，拿到客户信息
                EntityWrapper<ReleCustomerTag> ewByReleCustomerTag = new EntityWrapper<ReleCustomerTag>();
                ewByReleCustomerTag.where("status = {0}",Const.ONE);
                ewByReleCustomerTag.in("tag_id",tagList);
                List<ReleCustomerTag> releCustomerTagList = releCustomerTagService.selectList(ewByReleCustomerTag);

                List<ChatRecords> chatRecordsList = new ArrayList<ChatRecords>();
                List<ArticleSubscription> articleSubscriptionList = new ArrayList<ArticleSubscription>();
                List<ArticleRead> articleReadList = new ArrayList<ArticleRead>();
                //组合条件之行为条件
                for (int i = 0; i < returnRuleTriggerActionList.size(); i++) {
                    RuleTriggerAction ruleTriggerAction = returnRuleTriggerActionList.get(i);
                    //行为(0=阅读,1=订阅,2=聊天)
                    Integer action = ruleTriggerAction.getAction();
                    //(0= >,1= < ,2= >=,3= <=,4= =)
                    Integer condition = ruleTriggerAction.getCondition();
                    //次数
                    Integer frequency = ruleTriggerAction.getFrequency();
                    switch(action){
                        case 0:
                            //去文章阅读表中，根据阅读次数是否满足。
                            EntityWrapper<ArticleRead> ewByArticleRead = new EntityWrapper<ArticleRead>();
                            ewByArticleRead.where("status = {0}",Const.ONE);
                            ewByArticleRead.groupBy("customer_id");
                            String conditionStrByAR = "";
                            if(condition ==0){
                                conditionStrByAR = ">";
                            }else if(condition ==1){
                                conditionStrByAR = "<";
                            }else if(condition == 2){
                                conditionStrByAR = ">=";
                            }else if(condition == 3){
                                conditionStrByAR = "<=";
                            }else{
                                conditionStrByAR = "=";
                            }
                            ewByArticleRead.having("customer_id "+conditionStrByAR+" "+frequency+"");
                            articleReadList = articleReadService.selectList(ewByArticleRead);
                            break;
                        case 1:
                            //去文章订阅表中，根据订阅次数是否满足。
                            EntityWrapper<ArticleSubscription> ewByArticleSubscription = new EntityWrapper<ArticleSubscription>();
                            ewByArticleSubscription.where("status = {0}",Const.ONE);
                            ewByArticleSubscription.groupBy("customer_id");
                            String conditionStrByAS = "";
                            if(condition ==0){
                                conditionStrByAS = ">";
                            }else if(condition ==1){
                                conditionStrByAS = "<";
                            }else if(condition == 2){
                                conditionStrByAS = ">=";
                            }else if(condition == 3){
                                conditionStrByAS = "<=";
                            }else{
                                conditionStrByAS = "=";
                            }
                            ewByArticleSubscription.having("customer_id "+conditionStrByAS+" "+frequency+"");
                            articleSubscriptionList = articleSubscriptionService.selectList(ewByArticleSubscription);
                            break;
                        case 2:
                            //去聊天记录表中，根据聊天次数是否满足。
                            EntityWrapper<ChatRecords> ewByChatRecords = new EntityWrapper<ChatRecords>();
                            ewByChatRecords.where("status = {0}",Const.ONE);
                            ewByChatRecords.groupBy("customer_id");
                            String conditionStr = "";
                            if(condition ==0){
                                conditionStr = ">";
                            }else if(condition ==1){
                                conditionStr = "<";
                            }else if(condition == 2){
                                conditionStr = ">=";
                            }else if(condition == 3){
                                conditionStr = "<=";
                            }else{
                                conditionStr = "=";
                            }
                            ewByChatRecords.having("customer_id "+conditionStr+" "+frequency+"");
                            chatRecordsList = chatRecordsService.selectList(ewByChatRecords);
                            break;
                        default:
                    }
                }
                //判断
                int chatRecordsListSize = chatRecordsList.size();
                int releCustomerTagSize = releCustomerTagList.size();
                int articleSubscriptionSize = articleSubscriptionList.size();
                int articleReadSize = articleReadList.size();
                int[] maxIndexArray = {chatRecordsListSize,releCustomerTagSize,articleSubscriptionSize,articleReadSize};
                Arrays.sort(maxIndexArray);
                int maxIndex = maxIndexArray[0];
                Long luckUserId = null;
                ArrayList<Long> luckUserList = new ArrayList<Long>();
                if(chatRecordsListSize ==maxIndex){
                    for (int i = 0; i < chatRecordsList.size(); i++) {
                        ChatRecords chatRecords = chatRecordsList.get(i);
                        Long customerId = chatRecords.getCustomerId();
                        boolean breakFalg = false;
                        if(releCustomerTagList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(articleSubscriptionList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(articleReadList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(breakFalg){
                            luckUserList.add(customerId);
                        }
                    }
                }else if(maxIndex == releCustomerTagSize){
                    for (int i = 0; i < releCustomerTagList.size(); i++) {
                        Long customerId = releCustomerTagList.get(i).getCustomerId();
                        boolean breakFalg = false;
                        if(chatRecordsList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(articleReadList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(articleSubscriptionList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(breakFalg){
                            luckUserList.add(customerId);
                        }
                    }
                }else if(maxIndex == articleSubscriptionSize){
                    for (int i = 0; i < articleSubscriptionList.size(); i++) {
                        Long customerId = articleSubscriptionList.get(i).getUserId();
                        boolean breakFalg = false;
                        if(chatRecordsList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(articleReadList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(releCustomerTagList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(breakFalg){
                            luckUserList.add(customerId);
                        }
                    }
                }else{
                    for (int i = 0; i < articleReadList.size(); i++) {
                        Long customerId = articleReadList.get(i).getUserId();
                        boolean breakFalg = false;
                        if(chatRecordsList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(articleSubscriptionList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(releCustomerTagList.contains(customerId)){
                            breakFalg = true;
                        }else{
                            breakFalg = false;
                        }
                        if(breakFalg){
                            luckUserList.add(customerId);
                        }
                    }
                }
                //查找用户
                EntityWrapper<Customer> ewByCustomer = new EntityWrapper<Customer>();
                ewByCustomer.where("status = {0}",Const.ONE);
                ewByCustomer.in("id",luckUserList);
                List<Customer> customersList = customerService.selectList(ewByCustomer);
                List<Long> wxContactIdList = new ArrayList<Long>();
                customersList.forEach(customer -> {
                    wxContactIdList.add(customer.getWxContactId());
                });
                List<WxContact> wxContactList = wxContactService.selectList(new EntityWrapper<WxContact>().where("status ={0}", Const.ONE).in("id", wxContactIdList));
                List<WxContactDto> wxContactDtoList = WxContactMapstruct.getInstance.toDtoList(wxContactList);
                returnRuleDto.setTitle(rule.getTitle());
                returnRuleDto.setContent(rule.getContent());
                returnRuleDto.setWxContactDtos(wxContactDtoList);
            }else{
                    return  returnRuleDto;
            }
        }
        return returnRuleDto;
    }

    @Transactional
    @Override
    public boolean save(RuleDto ruleDto) {
        boolean returnFalg = false;
        try {
            Long userId = SessionUtils.getUserId();
            Rule rule = new Rule();
            rule.setTitle(ruleDto.getTitle());
            rule.setContent(ruleDto.getContent());
            rule.setEndDate(ruleDto.getEndDate());
            rule.setStartDate(ruleDto.getStartDate());
            rule.setCreateUser(userId);
            rule.setUpdateUser(userId);
            super.insert(rule);
            Long ruleId = rule.getId();
            List<Long> customerTagsList = ruleDto.getCustomerTags();
            List<RuleCustomerLabel> addRuleCustomerLabelList = new ArrayList<RuleCustomerLabel>();
            customerTagsList.forEach(customerTagId ->{
                RuleCustomerLabel ruleCustomerLabel = new RuleCustomerLabel();
                ruleCustomerLabel.setRuleId(ruleId);
                ruleCustomerLabel.setTagId(customerTagId);
                ruleCustomerLabel.setCreateUser(userId);
                ruleCustomerLabel.setUpdateUser(userId);
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
                ruleTriggerAction.setCreateUser(userId);
                ruleTriggerAction.setUpdateUser(userId);
                addRuleTriggerActionList.add(ruleTriggerAction);
            });
            if(addRuleCustomerLabelList.size() > 0){
                ruleCustomerLabelService.insertOrUpdateAllColumnBatch(addRuleCustomerLabelList);
            }
            if (addRuleTriggerActionList.size() > 0 ){
                ruleTriggerActionService.insertOrUpdateAllColumnBatch(addRuleTriggerActionList);
            }
            returnFalg = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnFalg;
    }

    @Transactional
    @Override
    public boolean update(RuleDto ruleDto) {
        Long userId = SessionUtils.getUserId();
        Long ruleId = ruleDto.getId();
        Rule rule = new Rule();
        rule.setTitle(ruleDto.getTitle());
        rule.setContent(ruleDto.getContent());
        rule.setStartDate(ruleDto.getStartDate());
        rule.setEndDate(ruleDto.getEndDate());
        rule.setUpdateUser(userId);
        rule.setId(ruleId);
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
                    ruleCustomerLabelObj.setCreateUser(userId);
                    ruleCustomerLabelObj.setUpdateUser(userId);
                    addRuleCustomerLabelList.add(ruleCustomerLabelObj);
                }
            }
        });
        oldRuleCustomerLabelMap.forEach((key,value)->{
            RuleCustomerLabel oldRuleCustomerLabel =(RuleCustomerLabel) value;
            oldRuleCustomerLabel.setStatus(0);
            oldRuleCustomerLabel.setCreateUser(userId);
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
            Integer condition = ruleTriggerActionObj.getCondition();
            Integer frequency = ruleTriggerActionObj.getFrequency();
            String ruleIdAndActionId = ruleId.toString()+"@"+actionId.toString();
            if(oldRuleTriggerActionMap.containsKey(ruleIdAndActionId)){
                oldRuleTriggerActionMap.remove(ruleIdAndActionId);
            }else{
                RuleTriggerAction oldRuleTriggerAction = (RuleTriggerAction)oldRuleTriggerActionMap.get(ruleIdAndActionId);
                if(oldRuleTriggerAction ==null){
                    RuleTriggerAction newRuleTriggerActionObj = new RuleTriggerAction();
                    newRuleTriggerActionObj.setRuleId(ruleId);
                    newRuleTriggerActionObj.setAction(actionId);
                    newRuleTriggerActionObj.setCondition(condition);
                    newRuleTriggerActionObj.setFrequency(frequency);
                    newRuleTriggerActionObj.setCreateUser(userId);
                    newRuleTriggerActionObj.setUpdateUser(userId);
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
            ruleCustomerLabelService.insertOrUpdateBatch(addRuleCustomerLabelList);
        }
        if(updateRuleCustomerLabelList.size() > 0 ){
            ruleCustomerLabelService.insertOrUpdateBatch(updateRuleCustomerLabelList);
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
    public RuleDto findRuleByRuleId(Long ruleId) {
        Long userId = SessionUtils.getUserId();
        HashMap<String,Object> columnRuleMap = new HashMap<String,Object>(3);
        columnRuleMap.put("id",ruleId);
        columnRuleMap.put("status",Const.ONE);
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
        columnRuleCustomerLabelMap.put("status",Const.ONE);
        List<RuleCustomerLabel> ruleCustomerLabelList = ruleCustomerLabelService.selectByMap(columnRuleCustomerLabelMap);
        ArrayList<Long> customerTagsList = new ArrayList<Long>();
        ruleCustomerLabelList.forEach( ruleCustomerLabelObj -> {
            Long tagId = ruleCustomerLabelObj.getTagId();
            customerTagsList.add(tagId);
        });
        HashMap<String,Object> columnRuleTriggerActionMap = new HashMap<String,Object>(3);
        columnRuleTriggerActionMap.put("rule_id",ruleId);
        //0代表状态为删除，1代表状态为正常
        columnRuleTriggerActionMap.put("status",Const.ONE);
        List<RuleTriggerAction> ruleTriggerActionList = ruleTriggerActionService.selectByMap(columnRuleTriggerActionMap);
        returnRuleDto.setId(ruleId);
        returnRuleDto.setContent(rule.getContent());
        returnRuleDto.setTitle(rule.getTitle());
        returnRuleDto.setStartDate(rule.getStartDate());
        returnRuleDto.setEndDate(rule.getEndDate());
        returnRuleDto.setCustomerTags(customerTagsList);
        returnRuleDto.setTriggers(ruleTriggerActionList);
        return returnRuleDto;
    }

    @Override
    public Page<RuleDto> selectDtoPage(int pageNum, int pageSize, RuleDto ruleDto) {
        Page<RuleDto> page = new Page<>(pageNum,pageSize);
        List<RuleDto> ruleDtoList = baseMapper.selectDtoPage(page, ruleDto);
        page.setRecords(ruleDtoList);
        return page;
    }


}
