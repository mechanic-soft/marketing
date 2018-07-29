/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 上午9:58
 */
package cn.com.geasy.marketing.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Service 测试启动类
 *
 * @author phil
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.gitee.mechanic", "cn.com.geasy.marketing"})
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "cn.com.geasy.marketing.dao")
public class StartupTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartupTestApplication.class, args);
    }
}
