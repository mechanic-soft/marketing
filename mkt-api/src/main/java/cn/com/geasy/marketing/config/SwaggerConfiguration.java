/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:07
 */
package cn.com.geasy.marketing.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 基于Springfox 的 Swagger2 配置
 *
 * @author phil
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private static final String TITLE = "微信营销转化平台接口";
    private static final String DESCRIPTION = "Wechat Marketing Platform API";
    private static final String VERSION = "1.0.0";
    private static final String CONTACT_NAME = "Phil Cheng";
    private static final String CONTACT_URL = "https://gitee.com/tvi/marketing";
    private static final String CONTACT_EMAIL = "feng.cheng@techviewinfo.com";

    private static final String API_BASE_PACKAGE = "cn.com.geasy.marketing.api";

    @Bean
    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .apiInfo(apiInfo())
//                .tags(new Tag("Login", "用户接口"), getTags())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE))
//                .paths(PathSelectors.any())
//                .build();


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

//    @Bean
//    public Docket packageApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("app")
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("tech.yiyehu.modules.app.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    private Tag[] getTags() {
        return new Tag[]{
                new Tag("User", "用户接口"),
                new Tag("Role", "角色接口"),
                new Tag("Permission", "权限接口")
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .build();
    }

    private Contact contact() {
        return new Contact(
                CONTACT_NAME,
                CONTACT_URL,
                CONTACT_EMAIL
        );
    }
}
