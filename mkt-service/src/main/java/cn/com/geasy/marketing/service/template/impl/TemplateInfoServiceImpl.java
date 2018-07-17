//package cn.com.geasy.marketing.service.message.impl;
//
//import cn.com.geasy.marketing.dao.message.TemplateInfoMapper;
//import cn.com.geasy.marketing.domain.entity.message.TemplateInfo;
//import cn.com.geasy.marketing.service.message.MessageTemplateService;
//import cn.com.geasy.marketing.service.message.TemplateInfoService;
//import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//
///**
// * Created by Think on 2018/1/31.
// */
//@Service
//public class TemplateInfoServiceImpl extends SuperServiceImpl<TemplateInfoMapper, TemplateInfo> implements TemplateInfoService {
//
//    @Autowired
//    private MessageTemplateService messageTemplateService;
//
//    @Override
//    @Async
//    public void sendMessage(TemplateInfo templateInfo) {
//        messageTemplateService.sendTriggerNoticeMessage(templateInfo, SessionUtil.getCurrentWechatUserId());
//    }
//}
