/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.wechat.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.wechat.ChatRecordsMapper;
import cn.com.geasy.marketing.domain.dto.wechat.ChatRecordsDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxCustomerDto;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import cn.com.geasy.marketing.mapstruct.wechat.ChatRecordsMapstruct;
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

    @Override
    public String save(ChatRecordsDto chatRecordsDto) {
        ChatRecords chatRecords = ChatRecordsMapstruct.getInstance.toEntity(chatRecordsDto);
        boolean flag = this.insert(chatRecords);

        return flag?Const.SAVE_SUCCESS: Const.SAVE_FAIL;
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
}
