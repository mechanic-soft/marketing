/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午3:53
 */
package cn.com.geasy.marketing.service.wechat;

import cn.com.geasy.marketing.domain.dto.wechat.ChatRecordsDto;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import com.gitee.mechanic.mybatis.base.SuperService;

/**
 * 聊天记录Service
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
public interface ChatRecordsService extends SuperService<ChatRecords> {
    public String save(ChatRecordsDto chatRecordsDto);
}
