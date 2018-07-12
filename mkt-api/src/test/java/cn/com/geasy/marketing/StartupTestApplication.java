/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午3:10
 */
package cn.com.geasy.marketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.test.context.ActiveProfiles;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@ActiveProfiles("unit_test")
@SpringBootApplication(scanBasePackages = {"cn.com.geasy.marketing.api"})
public class StartupTestApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false);
        builder.sources(StartupApplication.class);
        return builder;
    }

    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class, args);
    }
}
