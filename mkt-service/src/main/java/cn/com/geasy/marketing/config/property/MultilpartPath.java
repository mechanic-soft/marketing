/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午7:37
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
@ConfigurationProperties("multipart")
@Data
public class MultilpartPath {
    private String imagePath;

    private String htmlPath;
}
