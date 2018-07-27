/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午3:53
 */
package cn.com.geasy.marketing.service.wechat;

import cn.com.geasy.marketing.domain.dto.wechat.WxContactSecondDto;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
public interface WxContactService extends SuperService<WxContact> {
    /**同步微信联系人信息
     * uin 是唯一字段，有则更新，没有则插入
     * @param list
     * @return
     */
    boolean  inserOrUpdateBatchByUin(List<WxContactSecondDto> list);
}
