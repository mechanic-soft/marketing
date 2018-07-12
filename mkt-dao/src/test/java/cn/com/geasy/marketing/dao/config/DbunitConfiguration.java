/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 上午10:53
 */
package cn.com.geasy.marketing.dao.config;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * DbUnit 配置
 *
 * @author phil
 * @version 1.0.0
 */
@Configuration
public class DbunitConfiguration {
    @Bean
    public DatabaseConfigBean config(){
        DatabaseConfigBean config = new DatabaseConfigBean();
        config.setAllowEmptyFields(true);
        return config;
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(
            DatabaseConfigBean dbUnitDatabaseConfig, DataSource dataSource) {
        DatabaseDataSourceConnectionFactoryBean bean = new DatabaseDataSourceConnectionFactoryBean(dataSource);
        bean.setDatabaseConfig(dbUnitDatabaseConfig);

        return bean;
    }

//    @Bean
//    public DatabaseConfig databaseConfig(){
//        DatabaseConfig databaseConfig = new DatabaseConfig();
//        databaseConfig.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
//        return databaseConfig;
//    }
}
