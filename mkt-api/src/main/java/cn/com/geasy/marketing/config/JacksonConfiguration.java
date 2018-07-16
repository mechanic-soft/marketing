/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 下午6:37
 */
package cn.com.geasy.marketing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitee.mechanic.json.mapper.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson 配置
 *
 * @author phil
 * @version 1.0.0
 */
@Configuration
public class JacksonConfiguration {
    /**
     * 自定义jsckson转换参数
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.getMapper();
    }
}
