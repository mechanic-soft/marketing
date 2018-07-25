/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午3:19
 */
package cn.com.geasy.marketing.dao.wechat;

import cn.com.geasy.marketing.domain.dto.wechat.WxCustomerDto;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperMapper;

import java.util.List;
import java.util.Map;

/**
 * 聊天记录Mapper
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
public interface ChatRecordsMapper extends SuperMapper<ChatRecords> {
    public List<WxCustomerDto> findUncontactCustomer(Page<WxCustomerDto> page,Map<String, Object> map);
}
