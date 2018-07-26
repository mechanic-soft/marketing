/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午7:47
 */
package cn.com.geasy.marketing.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 系统正常启动之后的监听器
 *
 * @author phil
 * @version 1.0.0
 */
@Component
public class StartupContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("-------------------------------------------");
        logger.info("| Wechat Marketing Platform started.");
        logger.info("-------------------------------------------");
    }
}
