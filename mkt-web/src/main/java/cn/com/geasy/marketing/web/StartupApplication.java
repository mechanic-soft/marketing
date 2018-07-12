/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午2:58
 */
package cn.com.geasy.marketing.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"cn.com.geasy.marketing.web"})
public class StartupApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false);
        builder.sources(StartupApplication.class);
        return builder;
    }

    public static void main(String[] args){
        SpringApplication.run(StartupApplication.class, args);
    }
}
