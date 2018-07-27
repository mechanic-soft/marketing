/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.wechat.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.wechat.ChatRecordsMapper;
import cn.com.geasy.marketing.domain.dto.wechat.ChatRecordsDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxCustomerDto;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import cn.com.geasy.marketing.mapstruct.wechat.ChatRecordsMapstruct;
import cn.com.geasy.marketing.service.customer.CustomerDynamicService;
import cn.com.geasy.marketing.service.wechat.ChatRecordsService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 聊天记录ServiceImpl
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class ChatRecordsServiceImpl extends SuperServiceImpl<ChatRecordsMapper, ChatRecords> implements ChatRecordsService {

    @Autowired
    private WxContactService wxContactService;

    @Autowired
    private ChatRecordsService chatRecordsService;
    @Autowired
    private CustomerDynamicService customerDynamicService;

    /**
     * 客户经理的权限ID
     */
    private final String ROLE_ID = "3";
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public String save(ChatRecordsDto chatRecordsDto) {
        ChatRecords chatRecords = ChatRecordsMapstruct.getInstance.toEntity(chatRecordsDto);
        try{
            Long roleId = SessionUtils.getRoleId();
            Long userId = SessionUtils.getUserId();
            if(ROLE_ID.equals(roleId.toString())){
                //判断 ，根据当前日期，客户，事件(聊天)，客户经理
                LocalDate localDate = LocalDate.now();
                Long customerId = chatRecordsDto.getCustomerId();
                String sql ="status={0} AND DATE_FORMAT(create_time, '%Y-%m-%d')={1} AND customer_id={2} AND user_id ={3} AND event={4} ";
                EntityWrapper<CustomerDynamic> ewByCustomerDynamic = new EntityWrapper<CustomerDynamic>();
                ewByCustomerDynamic.where(sql,Const.ONE,localDate,customerId,userId,(Const.ONE+Const.ONE));
                List<CustomerDynamic> CustomerDynamicList = customerDynamicService.selectList(ewByCustomerDynamic);
                if(CustomerDynamicList.size() == 0){
                    //插入一条记录到客户动态表
                    CustomerDynamic customerDynamic = new CustomerDynamic();
                    customerDynamic.setCustomerId(customerId);
                    //事件(0=阅读,1=订阅,2=联系)
                    customerDynamic.setEvent(2);
                    customerDynamic.setCreateUser(userId);
                    customerDynamic.setUserId(userId);
                    customerDynamic.setEventDate(LocalDateTime.now());
                    customerDynamicService.insert(customerDynamic);
                }
            }
            chatRecords.setCreateUser(userId);
            chatRecords.setUpdateUser(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.insert(chatRecords)?Const.SAVE_SUCCESS: Const.SAVE_FAIL;
    }


    @Override
    public Page<ChatRecordsDto> findChatRecordsByCondition(String username, String keyword, int pageNum) {
        Page<ChatRecordsDto> result = PageUtils.getPage(pageNum);
        if (StringUtils.isNotBlank(username)) {
            //根据客户ID以及当前登录用户ID
            EntityWrapper<ChatRecords> chatRecordEw = new EntityWrapper<ChatRecords>();
            chatRecordEw.where("create_user = {0}", SessionUtils.getUserId());
            chatRecordEw.andNew("wx_username ={0}", username);
            if (StringUtils.isNoneBlank(keyword)) {
                chatRecordEw.like("content", keyword);
            }
            //默认升序排列
            chatRecordEw.orderBy("send_time");
            //执行查询
            Page<ChatRecords> page = PageUtils.getPage(pageNum);
            page = chatRecordsService.selectPage(page, chatRecordEw);
            //将对应实体转换为对应实体的DTO
            List<ChatRecordsDto> chatRecordsDtos = ChatRecordsMapstruct.getInstance.toDtoList(page.getRecords());
            result = PageUtils.getPage(page, chatRecordsDtos);
        }

        return result;
    }

    @Override
    public Page<WxCustomerDto> getChatConsumersList(Integer isSort,Integer pageNum) {
        //查找当前登录用户最近未联系的
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",SessionUtils.getUserId());
        map.put("isSort",isSort);
        Page<WxCustomerDto> page = PageUtils.getPage(pageNum);
        List<WxCustomerDto> list = baseMapper.findUncontactCustomer(page,map);
        return page.setRecords(list);
    }

    @Override
    public Page<WxCustomerDto> getChatConsumersByWxContactIdList(List<Long> wxContactIdList,LocalDate startTime,LocalDate endTime, Integer pageNum) {
        Map<String,Object> map = new HashMap<String,Object>(5);
        StringBuffer orderByStr = new StringBuffer();
        orderByStr.append("(',");
        for (int i = 0; i < wxContactIdList.size(); i++) {
            String wxContactId = wxContactIdList.get(i).toString();
            orderByStr.append(wxContactId+",");
            if(i==wxContactIdList.size()-1){
                orderByStr.append("'");
            }
        }
        orderByStr.append(",CONCAT(',',wc.id,','))");
        map.put("wxContactIdList",wxContactIdList);
        map.put("orderByStr",orderByStr);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        Page<WxCustomerDto> page = PageUtils.getPage(pageNum);
        List<WxCustomerDto> list = baseMapper.getChatConsumersByWxContactIdList(page,map);
        return page.setRecords(list);
    }
}
