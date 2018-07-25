/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.wechat.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.wechat.ChatRecordsMapper;
import cn.com.geasy.marketing.dao.wechat.WxContactMapper;
import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.dto.wechat.ChatRecordsDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxCustomerDto;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.mapstruct.wechat.WxContactMapstruct;
import cn.com.geasy.marketing.mapstruct.wechat.ChatRecordsMapstruct;
import cn.com.geasy.marketing.service.customer.CustomerDynamicService;
import cn.com.geasy.marketing.service.wechat.ChatRecordsService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public Page<ChatRecordsDto> findChatRecordsByCondition(String nickname,String keyword,int pageNum) {
        //根据昵称
        //步骤一:根据微信昵称匹配对应的微信联系人记录
        EntityWrapper<WxContact> ew=new EntityWrapper<WxContact>();
        ew.where("user_id = {0}", SessionUtils.getUserId());
        if(StringUtils.isNotBlank(nickname)){
            ew.andNew("nickname = {0}",nickname);
        }
        List<WxContact> list = wxContactService.selectList(ew);
        Page<ChatRecordsDto> result = PageUtils.getPage(pageNum);
        if(!CollectionUtils.isEmpty(list)){
           Long customerId = list.get(0).getId();
           //根据客户ID以及当前登录用户ID
           EntityWrapper<ChatRecords> chatRecordEw=new EntityWrapper<ChatRecords>();
           chatRecordEw.where("create_user = {0}", SessionUtils.getUserId());
           if(null != customerId){
               chatRecordEw.andNew("customer_id ={0}",customerId);
           }
           if(StringUtils.isNoneBlank(keyword)){
               chatRecordEw.like("content",keyword);
           }
           //默认升序排列
            chatRecordEw.orderBy("send_time");
           //执行查询
            Page<ChatRecords> page = PageUtils.getPage(pageNum);
            page = chatRecordsService.selectPage(page,chatRecordEw);
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
}
