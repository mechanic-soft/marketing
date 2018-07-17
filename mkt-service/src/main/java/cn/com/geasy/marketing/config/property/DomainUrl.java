/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午7:40
 */
package cn.com.geasy.marketing.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Component
@ConfigurationProperties("domain-url")
@Data
public class DomainUrl {
    private String pageUrl;

    private String apiUrl;

    private String domain;
}
