/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 上午11:54
 */
package cn.com.geasy.marketing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Spring Boot 启动类， 运行该类启动本工程。
 *
 * @author phil
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.gitee.mechanic", "cn.com.geasy.marketing"})
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "cn.com.geasy.marketing.dao")
public class StartupApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false);
        builder.sources(StartupApplication.class);
        return builder;
    }

    @Bean
    public FilterRegistrationBean myCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    public static void main(String[] args){
        SpringApplication.run(StartupApplication.class, args);
    }
}
