/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:01
 */
package cn.com.geasy.marketing.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Component
@Data
@ConfigurationProperties(prefix = "message")
public class MpMessageTemplate implements Serializable {
    private String noticeTemplateId;

    private String articleCreateNoticeTemplateId;

    private String triggerMessageNoticeTemplateId;

    private String roleLevelApproveTemplateId;
}
