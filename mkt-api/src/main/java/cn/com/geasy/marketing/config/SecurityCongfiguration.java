/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/19 下午2:04
 */
package cn.com.geasy.marketing.config;

import cn.com.geasy.marketing.security.MyAccessDecisionManager;
import cn.com.geasy.marketing.security.MyAuthenctiationFailureHandler;
import cn.com.geasy.marketing.security.MyAuthenctiationSuccessHandler;
import cn.com.geasy.marketing.security.MyInvocationSecurityMetadataSourceService;
import cn.com.geasy.marketing.service.security.MyUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Spring Security 配置
 *
 * @author phil
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
public class SecurityCongfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userService() {
        return new MyUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2*/**", "/swagger*/**", "/v2/api-docs", "/webjars/**", "/*.ico").permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O filterSecurityInterceptor) {
                        filterSecurityInterceptor.setSecurityMetadataSource(mySecurityMetadataSource());
                        filterSecurityInterceptor.setAccessDecisionManager(myAccessDecisionManager());
                        return filterSecurityInterceptor;
                    }
                })
                .and()
                .formLogin()
                .failureUrl("/login?error")
                .loginPage("/unauthor")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenctiationSuccessHandler())
                .failureHandler(myAuthenctiationFailureHandler())
                .permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable();
    }

    @Bean
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler() {
        return new MyAuthenctiationSuccessHandler();
    }

    @Bean
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler() {
        return new MyAuthenctiationFailureHandler();
    }

    @Bean
    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        return new MyInvocationSecurityMetadataSourceService();
    }

    @Bean
    public AccessDecisionManager myAccessDecisionManager() {
        return new MyAccessDecisionManager();
    }
}
