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
        StringBuffer sb = new StringBuffer(200);
        sb.append("in(");
        wxContacts.forEach(wxContact ->{
            nickNames.add(wxContact.getNickName());
            sb.append("'"+wxContact.getNickName()+"',");
        });
        log.info("微信昵称：");
        log.info(sb.substring(0,sb.length()-1) + ")");


        List<WxContact> existWxContacts =existWxContacts(nickNames);
        log.debug(existWxContacts.size()+"");
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
        int updateIndex = 0;

        for(WxContact wxContactUpdate:wxContactsUpdate) {
            EntityWrapper wxContactUpdateWrapper = new EntityWrapper<>();
            wxContactUpdateWrapper.eq("user_id", SessionUtils.getUserId().toString()).eq("BINARY nick_name", wxContactUpdate.getNickName());
            log.info("updateIndex:" + updateIndex);
            log.info(wxContactUpdate.toString());
            this.update(wxContactUpdate, wxContactUpdateWrapper);
            updateIndex++;
        }
        return true;
    }

    /***
     * mysql支持in 个数受限制
     * @param nickNames
     * @return
     */
    private List<WxContact> existWxContacts(List<String> nickNames) {
        int pageSize = 100;
        int totals = nickNames.size();
        int pageCount = getpageCount(totals,pageSize);
        List<WxContact> existWxContacts = new ArrayList<>(300);
        for(int i=0;i<pageCount;i++){
            EntityWrapper ew =new EntityWrapper<>();
            int fromIndex = 0+i*pageSize;
            int toIndex = ((i==pageCount-1)?(totals % pageSize):99)+i*pageSize;
            log.info("fromIndex:"+ fromIndex + "  toIndex:" + toIndex);
            ew.in("nick_name",nickNames.subList(fromIndex,toIndex)).eq("user_id",SessionUtils.getUserId().toString());
            existWxContacts.addAll(this.selectList(ew));
        }

        return existWxContacts;
    }

    private int getpageCount(int totals, int pageSize) {
        int pageCount = (totals/pageSize);
        if (totals % pageSize != 0){
            pageCount ++;
        }

        return pageCount;
    }


}
