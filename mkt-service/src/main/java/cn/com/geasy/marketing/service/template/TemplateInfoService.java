package cn.com.geasy.marketing.service.template;

import cn.com.geasy.marketing.domain.entity.message.MessageTemplate;
import com.gitee.mechanic.mybatis.base.SuperService;

/**
 * Created by Think on 2018/1/31.
 */
public interface TemplateInfoService extends SuperService<MessageTemplate> {

    /**
     * 发生模板消息
     * @param templateInfo
     */
    void sendMessage(MessageTemplate templateInfo);
}
