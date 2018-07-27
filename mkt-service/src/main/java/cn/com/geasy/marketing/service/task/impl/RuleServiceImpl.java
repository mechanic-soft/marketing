/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.task.impl;


import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.task.RuleMapper;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.domain.dto.task.RuleDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxCustomerDto;
import cn.com.geasy.marketing.domain.entity.article.ArticleRead;
import cn.com.geasy.marketing.domain.entity.article.ArticleSubscription;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import cn.com.geasy.marketing.domain.entity.customer.ReleCustomerTag;
import cn.com.geasy.marketing.domain.entity.task.Rule;
import cn.com.geasy.marketing.domain.entity.task.RuleCustomerLabel;
import cn.com.geasy.marketing.domain.entity.task.RuleTriggerAction;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.mapstruct.wechat.WxContactMapstruct;
import cn.com.geasy.marketing.service.article.ArticleReadService;
import cn.com.geasy.marketing.service.article.ArticleSubscriptionService;
import cn.com.geasy.marketing.service.customer.CustomerDynamicService;
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
import com.gitee.mechanic.mybatis.utils.PageUtils;
import com.mysql.jdbc.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

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
    private CustomerService customerService;

    @Autowired
    private WxContactService wxContactService;

    @Autowired
    private CustomerDynamicService customerDynamicService;

    @Override
    public RuleDto getRemindings() {
        RuleDto returnRuleDto = new RuleDto();
        try{
            Long userId = SessionUtils.getUserId();
            LocalDate newLocalDate = LocalDate.now();
            EntityWrapper<Rule> ew=new EntityWrapper<Rule>();
            ew.where("status = {0}",Const.ONE);
            ew.orderBy("end_date",false);
            //根据规则的结束时间排序，获取规则。
            List<Rule> ruleList = this.selectList(ew);
            List<RuleCustomerLabel> returnRuleCustomerLabelList = new ArrayList<RuleCustomerLabel>();
            List<RuleTriggerAction> returnRuleTriggerActionList = new ArrayList<RuleTriggerAction>();
            if(ruleList.size() > 0 ){
                //取出排序后最前一个规则
                Rule rule = ruleList.get(0);
                Long ruleId = rule.getId();
                LocalDate endDate = rule.getEndDate();
                LocalDate startDate = rule.getStartDate();
                //根据当前时间判断是否符合在规则时间内
                if(newLocalDate.isBefore(endDate) || newLocalDate.isEqual(endDate)){
                    returnRuleDto.setTitle(rule.getTitle());
                    returnRuleDto.setContent(rule.getContent());
                    returnRuleDto.setStartDate(startDate);
                    returnRuleDto.setEndDate(endDate);
                    HashMap<String,Object> columnMap = new HashMap<String,Object>(2);
                    columnMap.put("rule_id",ruleId);
                    columnMap.put("status",Const.ONE);
                    //查询规则中的标签
                    returnRuleCustomerLabelList = ruleCustomerLabelService.selectByMap(columnMap);
                    //查询规则中的行为
                    returnRuleTriggerActionList = ruleTriggerActionService.selectByMap(columnMap);
                    //查询标签条件
                    boolean customerTagFlag = true;
                    List<Long> releCustomerTagList = new ArrayList<Long>();
                    if(returnRuleCustomerLabelList.size() > 0 ){
                        EntityWrapper<ReleCustomerTag> ewByReleCustomerTag = new EntityWrapper<ReleCustomerTag>();
                        StringBuffer sql =  new StringBuffer();
                        sql.append("status = 1 ");
                        for (int i = 0; i < returnRuleCustomerLabelList.size(); i++) {
                            RuleCustomerLabel ruleCustomerLabel = returnRuleCustomerLabelList.get(i);
                            sql.append("AND tag_id = "+ruleCustomerLabel.getTagId()+"");
                        }
                        ewByReleCustomerTag.where(sql.toString());
                        //根据标签主键关联客户标签关联表，拿到客户信息
                        List<ReleCustomerTag> customerTagList = releCustomerTagService.selectList(ewByReleCustomerTag);
                        customerTagList.forEach(customerTagObj->{
                            releCustomerTagList.add(customerTagObj.getCustomerId());
                        });
                    }
                    List<Long> customerIdByReadActionList = new ArrayList<>();
                    List<Long> customerIdBySubActionList = new ArrayList<>();
                    List<Long> customerIdByTalkActionList = new ArrayList<>();
                    List<Long> luckCostomerList = new ArrayList<>();
                    boolean customerIdByReadActionListFlag = false;
                    boolean customerIdBySubActionFlag = false;
                    boolean customerIdByTalkActionFlag = false;
                    //查询行为条件
                    for (int i = 0; i < returnRuleTriggerActionList.size(); i++) {
                        RuleTriggerAction ruleTriggerAction = returnRuleTriggerActionList.get(i);
                        //行为(0=阅读,1=订阅,2=聊天)
                        Integer action = ruleTriggerAction.getAction();
                        //(0= >,1= < ,2= >=,3= <=,4= =)
                        Integer condition = ruleTriggerAction.getCondition();
                        //次数
                        Integer frequency = ruleTriggerAction.getFrequency();
                        String conditionStr = getSymbol(condition);
                        CustomerDynamicDto paraCdd = new CustomerDynamicDto();
                        paraCdd.setSymbol(conditionStr);
                        paraCdd.setFrequency(frequency);
                        paraCdd.setStatus(Const.ONE);
                        paraCdd.setStartTime(startDate);
                        paraCdd.setEndTime(endDate);
                        if("0".equals(action.toString())){
                            paraCdd.setEvent(action);
                            customerIdByReadActionList = customerDynamicService.getCustomerIdByCustomerInteractionDynamic(paraCdd);
                            customerIdByReadActionListFlag = true;
                        }else if ("1".equals(action.toString())){
                            paraCdd.setEvent(action);
                            customerIdBySubActionList = customerDynamicService.getCustomerIdByCustomerInteractionDynamic(paraCdd);
                            customerIdBySubActionFlag = true;
                        }else if ("2".equals(action.toString())){
                            paraCdd.setEvent(action);
                            customerIdByTalkActionList = customerDynamicService.getCustomerIdByCustomerInteractionDynamic(paraCdd);
                            customerIdByTalkActionFlag = true;
                        }
                    }
                    //取出集合的并集
                    if(releCustomerTagList.size() > 0 ){
                        if(customerIdByReadActionListFlag){
                            releCustomerTagList.retainAll(customerIdByReadActionList);
                            if(customerIdByReadActionList.size() > 0){
                                luckCostomerList.addAll(customerIdByReadActionList);
                            }else{
                                return returnRuleDto;
                            }
                        }
                        if(customerIdBySubActionFlag){
                            releCustomerTagList.retainAll(customerIdBySubActionList);
                            if(customerIdBySubActionList.size() > 0){
                                luckCostomerList.addAll(customerIdBySubActionList);
                            }else{
                                return returnRuleDto;
                            }
                        }
                        if(customerIdByTalkActionFlag){
                            releCustomerTagList.retainAll(customerIdByTalkActionList);
                            if(customerIdByTalkActionList.size() > 0){
                                luckCostomerList.addAll(customerIdByTalkActionList);
                            }else{
                                return returnRuleDto;
                            }
                        }
                    }
                    //查找用户
                    EntityWrapper<Customer> ewByCustomer = new EntityWrapper<Customer>();
                    ewByCustomer.in("id",luckCostomerList);
                    List<Customer> customersList = customerService.selectList(ewByCustomer);
                    List<Long> wxContactIdList = new ArrayList<Long>();
                    customersList.forEach(customer -> {
                        wxContactIdList.add(customer.getWxContactId());
                    });
                    List<WxContactDto> wxContactDtoList = new ArrayList<WxContactDto>();
                    if(wxContactIdList.size() > 0){
                        List<WxContact> wxContactList = wxContactService.selectList(new EntityWrapper<WxContact>()
                                .where("status ={0}", Const.ONE)
                                .in("id", wxContactIdList));
                        wxContactDtoList = WxContactMapstruct.getInstance.toDtoList(wxContactList);
                    }
                    returnRuleDto.setWxContactDtos(wxContactDtoList);
                }
            }
            return returnRuleDto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnRuleDto;
    }

    /**
     * 根据数字获取对应的算术符号
     * @param condition 数字
     * @return String symbolStr
     */
    private String getSymbol(Integer condition) {
        String symbolStr;
        if(condition ==0){
            symbolStr = ">";
        }else if(condition ==1){
            symbolStr = "<";
        }else if(condition == 2){
            symbolStr = ">=";
        }else if(condition == 3){
            symbolStr = "<=";
        }else{
            symbolStr = "=";
        }
        return symbolStr;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
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
