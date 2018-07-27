/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.wechat.impl;

import cn.com.geasy.marketing.dao.wechat.WxContactMapper;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactSecondDto;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.mapstruct.wechat.WxContactSecondMapstruct;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 */
@Slf4j
@Service
public class WxContactServiceImpl extends SuperServiceImpl<WxContactMapper, WxContact> implements WxContactService {

    /**
     * 同步微信联系人信息
     * uin 是唯一字段，有则更新，没有则插入
     * @param list
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public boolean inserOrUpdateBatchByUin(List<WxContactSecondDto> list) {

        List<WxContact> wxContacts = WxContactSecondMapstruct.getInstance.toEntityList(list);
        List<Long> uins = new ArrayList<>();
        List<Long> existUnis = new ArrayList<>();//数据库中已经存在的uin
        wxContacts.forEach(wxContact ->{
            uins.add(wxContact.getUin());
        });
        EntityWrapper ew =new EntityWrapper<>();
        ew.in("uin",uins);
        List<WxContact> existWxContacts =this.selectList(ew);
        existWxContacts.forEach(existWxContact->{
            existUnis.add(existWxContact.getUin());
        });
        log.debug(existUnis.toString());

        List<WxContact> wxContactsInsert = new ArrayList<>();
        List<WxContact> wxContactsUpdate = new ArrayList<>();
        for (WxContact item:wxContacts) {
            item.setUserId(SessionUtils.getUserId());
            if(existUnis.contains(item.getUin())){
                item.setUpdateTime(LocalDateTime.now());
                item.setUpdateUser(SessionUtils.getUserId());
                wxContactsUpdate.add(item);
            }else {
                item.setCreateTime(LocalDateTime.now());
                item.setCreateUser(SessionUtils.getUserId());
                wxContactsInsert.add(item);
            }
        }
        //uin 是唯一字段，有则更新，没有则插入
        if(CollectionUtils.isNotEmpty(wxContactsInsert)) {
            this.insertBatch(wxContactsInsert);
        }
        wxContactsUpdate.forEach(wxContactUpdate->{
            EntityWrapper wxContactUpdateWrapper =new EntityWrapper<>();
            wxContactUpdateWrapper.eq("uin",wxContactUpdate.getUin());
            this.update(wxContactUpdate,wxContactUpdateWrapper);
        });
        return true;
    }
}
