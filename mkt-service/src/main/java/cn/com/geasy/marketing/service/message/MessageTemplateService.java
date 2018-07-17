package cn.com.geasy.marketing.service.message;

import cn.com.geasy.marketing.domain.entity.article.Article;
import cn.com.geasy.marketing.domain.entity.message.MessageTemplate;

/**
 * Created by Think on 2018/1/29.
 */
public interface MessageTemplateService {

    /**
     * 文章新建，通知他对应的订阅人
     * @param article
     */
    void sendCreateArticleMassage(Article article, Long sharedId);

    /**
     * 收到触发实时消息
     * @param messageTemplate
     * @param currentUserId
     */
    void sendTriggerNoticeMessage(MessageTemplate messageTemplate, Long currentUserId);

    /**
     * 定时任务
     * @param messageTemplate
     */
    void sendSchedulerMessage(MessageTemplate messageTemplate);

    /**
     * 角色改变通知
     * @param openId
     * @param oldRole
     * @param newRole
     */
    void sendRoleChangeMessage(String nickName, String openId, String oldRole, String newRole);
}
