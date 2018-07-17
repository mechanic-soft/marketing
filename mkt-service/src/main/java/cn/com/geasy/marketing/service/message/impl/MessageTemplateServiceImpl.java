package cn.com.geasy.marketing.service.message.impl;

import cn.com.geasy.marketing.config.property.DomainUrl;
import cn.com.geasy.marketing.config.property.MpMessageTemplate;
import cn.com.geasy.marketing.dao.message.MessageTemplateMapper;
import cn.com.geasy.marketing.domain.entity.article.Article;
import cn.com.geasy.marketing.domain.entity.message.MessageTemplate;
import cn.com.geasy.marketing.domain.entity.wechat.MpUser;
import cn.com.geasy.marketing.service.message.MessageTemplateService;
import cn.com.geasy.marketing.service.wechat.MpUserService;
import cn.com.geasy.marketing.utils.*;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Think on 2018/1/29.
 * 消息模板
 */
@Service
@Slf4j
public class MessageTemplateServiceImpl extends SuperServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {

    @Autowired
    private MpUserService mpUserService;

    @Autowired
    private MpMessageTemplate mpMessageTemplate;

    @Autowired
    private DomainUrl domainUrl;

    /**
     * 文章新建，通知他对应的订阅人
     *
     * @param article
     */
    @Override
    public void sendCreateArticleMassage(Article article, Long shareId) {
        if (article == null || article.getUserId() == null) {
            log.error("新建文章，文章为空通知失败");
            return;
        }
        if (StringUtils.isEmpty(mpMessageTemplate.getArticleCreateNoticeTemplateId())) {
            log.error("新建文章通知的模板id为空");
            return;
        }
        List<MpUser> mpUsers = mpUserService.findMpUserBySubcriptionUserId(article.getUserId());
        if (CollectionUtils.isEmpty(mpUsers)) {
            return;
        }
        MpUser wechatUser = mpUserService.selectById(article.getUserId());


        String url = domainUrl.getApiUrl() + "/weixin/oauth/article/detail?id=" + article.getId() + "&sharedInfoId=" + shareId;
        if (StringUtils.isEmpty(mpMessageTemplate.getArticleCreateNoticeTemplateId())) {
            log.error("articleCreateNoticeTemplateId is null ");
            return;
        }
        if (CollectionUtils.isEmpty(mpUsers)) {
            log.debug("找不到理财经理");
            return;
        }
        for (MpUser mpUser : mpUsers) {
            Map<String, String> detail = MessageTemplateUtil.getMessageDetailByKeyword("你收到了订阅人" + wechatUser.getMpNickname() + "新发布的一篇文章", "详情点击查看", article.getTitle(),
                    DateUtils.getDate2FormatString(new Date(), "yyyy-MM-dd"));
            if (mpUser.getMpSubscribe() != null && mpUser.getMpSubscribe() == 1) {
                String text = MessageTemplateUtil.getTemplateMessage(mpUser.getMpOpenid(), mpMessageTemplate.getArticleCreateNoticeTemplateId(), url, detail);
                MpServiceApi.sendTemplateMessage(MpTokenUtil.token, text);
            }
        }
    }

    /**
     * 收到触发消息
     *
     * @param messageTemplate
     * @param currentWechatUserId
     * @param currentWechatUserId
     */
    @Override
    public void sendTriggerNoticeMessage(MessageTemplate messageTemplate, Long currentWechatUserId) {
        if (StringUtils.isEmpty(mpMessageTemplate.getTriggerMessageNoticeTemplateId())) {
            return;
        }
        List<MpUser> wechatUserList = mpUserService.findMpUserManager();
        if (CollectionUtils.isEmpty(wechatUserList)) {
            return;
        }
        for (MpUser wechat : wechatUserList) {
            Map<String, String> detail = MessageTemplateUtil.getMessageDetailByKeyword(messageTemplate.getFirstMsg(), messageTemplate.getRemarkMsg(), wechat.getMpNickname(), messageTemplate.getContent());
            String text = MessageTemplateUtil.getTemplateMessage(wechat.getMpOpenid(), mpMessageTemplate.getTriggerMessageNoticeTemplateId(), messageTemplate.getUrl(), detail);
            MpServiceApi.sendTemplateMessage(getToken(), text);
        }
    }

    /**
     * 收到触发消息
     *
     * @param messageTemplate
     */
    @Override
    public void sendSchedulerMessage(MessageTemplate messageTemplate) {

        if (StringUtils.isEmpty(mpMessageTemplate.getNoticeTemplateId())) {
            return;
        }
        List<MpUser> wechatUserList = mpUserService.findMpUserManager();
        if (CollectionUtils.isEmpty(wechatUserList)) {
            return;
        }

        for (MpUser wechat : wechatUserList) {
            Map<String, String> detail = MessageTemplateUtil.getMessageDetailByKeyword(messageTemplate.getFirstMsg(), messageTemplate.getRemarkMsg(), wechat.getMpNickname(), messageTemplate.getContent());
            String text = MessageTemplateUtil.getTemplateMessage(wechat.getMpOpenid(), mpMessageTemplate.getNoticeTemplateId(), messageTemplate.getUrl(), detail);
            MpServiceApi.sendTemplateMessage(getToken(), text);
        }
    }

    /**
     * 角色改变通知
     *
     * @param openId
     * @param oldRole
     * @param newRole
     */
    @Override
    public void sendRoleChangeMessage(String nickName, String openId, String oldRole, String newRole) {
        if (StringUtils.isEmpty(mpMessageTemplate.getRoleLevelApproveTemplateId())) {
            return;
        }
        String first = nickName + "你好！你在系统的角色等级发生更改";
        String remark = "你有什么疑问可以联系我们";
        Map<String, String> detail = MessageTemplateUtil.getMessageDetailByKeyword(first, remark, oldRole, newRole);
        String text = MessageTemplateUtil.getTemplateMessage(openId, mpMessageTemplate.getRoleLevelApproveTemplateId(), null, detail);
        MpServiceApi.sendTemplateMessage(getToken(), text);
    }

    private String getToken() {
        String url = domainUrl.getDomain() + "/anxin/weixin/get/token";
        Map<String, Object> map = HttpUtils.get(url);
        return map.get("data").toString();
    }


}
