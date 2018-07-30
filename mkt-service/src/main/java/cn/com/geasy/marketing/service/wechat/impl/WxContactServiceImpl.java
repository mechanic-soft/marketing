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
     * 以当前用户userId 下，昵称判断唯一性，有则更新，没有则插入 (存在唯一性问题)
     *
     * @param list
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public boolean inserOrUpdateBatchByUin(List<WxContactSecondDto> list) {

        List<WxContact> wxContacts = WxContactSecondMapstruct.getInstance.toEntityList(list);
        List<String> nickNames = new ArrayList<>();
        List<String> existNickNames = new ArrayList<>();//数据库中已经存在的昵称
        wxContacts.forEach(wxContact ->{
            nickNames.add(wxContact.getNickName());
        });
        EntityWrapper ew =new EntityWrapper<>();
        ew.eq("user_id",SessionUtils.getUserId().toString()).in("BINARY nick_name",nickNames);
        List<WxContact> existWxContacts =this.selectList(ew);
        existWxContacts.forEach(existWxContact->{
            existNickNames.add(existWxContact.getNickName());
        });
        log.debug(existNickNames.toString());

        List<WxContact> wxContactsInsert = new ArrayList<>();
        List<WxContact> wxContactsUpdate = new ArrayList<>();
        for (WxContact item:wxContacts) {
            item.setUserId(SessionUtils.getUserId());
            if(existNickNames.contains(item.getNickName())){
                item.setUpdateTime(LocalDateTime.now());
                item.setUpdateUser(SessionUtils.getUserId());
                wxContactsUpdate.add(item);
            }else {
                item.setCreateTime(LocalDateTime.now());
                item.setCreateUser(SessionUtils.getUserId());
                wxContactsInsert.add(item);
            }
        }
        // 以当前用户userId 下，昵称判断唯一性，有则更新，没有则插入 (存在唯一性问题)
        if(CollectionUtils.isNotEmpty(wxContactsInsert)) {
            this.insertBatch(wxContactsInsert);
        }
        wxContactsUpdate.forEach(wxContactUpdate->{
            EntityWrapper wxContactUpdateWrapper =new EntityWrapper<>();
            wxContactUpdateWrapper.eq("user_id",SessionUtils.getUserId().toString()).eq("BINARY nick_name",wxContactUpdate.getNickName());
            this.update(wxContactUpdate,wxContactUpdateWrapper);
        });
        return true;
    }
}
