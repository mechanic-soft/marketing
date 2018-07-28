/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/24 下午12:11
 */
package cn.com.geasy.marketing.utils;

import com.google.common.collect.Maps;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 同步工具类
 *
 * @author phil
 * @version 1.0.0
 */
public class SynchronizeUtils {

    private final static Logger logger = LoggerFactory.getLogger(SynchronizeUtils.class);

    /**
     * 发送标签到安信服务号
     *
     * @param name 标签
     * @return boolean
     */
    public static boolean sendTag(String name) {

//        if (tag.getTypeId() == )

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> queryMap = Maps.newHashMap();
        queryMap.put("userId", "1");
        queryMap.put("name", name);
        queryMap.put("type", "1");

        logger.debug("Starting send tag '" + name + "' to Trace....");

        HttpResponse response = HttpRequest.get("http://anxin.trace.g-easy.com.cn/anxin-management/tag/tagCreate")
                .contentType("application/json;charset=UTF-8")
                .query(queryMap)
                .send();

        logger.debug("Send tag done.");

        return response.statusCode() == HttpStatus.HTTP_OK;
    }
}
