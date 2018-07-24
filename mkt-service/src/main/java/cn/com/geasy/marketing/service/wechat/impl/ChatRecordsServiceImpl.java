/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.wechat.impl;

import cn.com.geasy.marketing.dao.wechat.ChatRecordsMapper;
import cn.com.geasy.marketing.dao.wechat.WxContactMapper;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.service.wechat.ChatRecordsService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 聊天记录ServiceImpl
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class ChatRecordsServiceImpl extends SuperServiceImpl<ChatRecordsMapper, ChatRecords> implements ChatRecordsService {

}
