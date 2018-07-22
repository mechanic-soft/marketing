/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/19 下午2:04
 */
package cn.com.geasy.marketing.config;

import cn.com.geasy.marketing.security.MyAuthenctiationFailureHandler;
import cn.com.geasy.marketing.security.MyAuthenctiationSuccessHandler;
import cn.com.geasy.marketing.security.MyFilterSecurityInterceptor;
import cn.com.geasy.marketing.service.security.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Bean
    UserDetailsService userService() {
        return new MyUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService()).passwordEncoder(new BCryptPasswordEncoder());
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    public <O extends FilterSecurityInterceptor> O postProcess(
//                            O filterSecurityInterceptor) {
//                        filterSecurityInterceptor.setSecurityMetadataSource(mySecurityMetadataSource());
//                        filterSecurityInterceptor.setAccessDecisionManager(myAccessDecisionManager());
//                        return filterSecurityInterceptor;
//                    }
//                })
//                .formLogin()
//                .failureUrl("/login?error")
//                .loginPage("/unauthor")
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler(myAuthenctiationSuccessHandler())
//                .failureHandler(myAuthenctiationFailureHandler())
//                .and()
//                .antMatcher("/swagger*/**")
//                .antMatcher("/v2/api-docs")
//                .antMatcher("/webjars/**")
//                .antMatcher("/*.ico")
                .authorizeRequests()
                .antMatchers("/swagger*/**", "/v2/api-docs", "/webjars/**", "/*.ico", "/login", "/unauthor").permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/unauthor")
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler(myAuthenctiationSuccessHandler())
//                .failureHandler(myAuthenctiationFailureHandler())
//                .successForwardUrl("/swagger-ui.html")
                .and()
                .logout().permitAll()
                .and().csrf().disable();

        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    public <O extends FilterSecurityInterceptor> O postProcess(
//                            O filterSecurityInterceptor) {
//                        filterSecurityInterceptor.setSecurityMetadataSource(mySecurityMetadataSource());
//                        filterSecurityInterceptor.setAccessDecisionManager(myAccessDecisionManager());
//                        return filterSecurityInterceptor;
//                    }
//                })
//                .anyRequest().authenticated() //任何请求,登录后可以访问
//                .and()
//                .formLogin()
//                .failureUrl("/login?error")
//                .loginPage("/unauthor")
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler(myAuthenctiationSuccessHandler())
//                .failureHandler(myAuthenctiationFailureHandler())
//                .permitAll()
//                .and()
//                .logout().permitAll()
//                .and().csrf().disable();
//    }


    @Bean
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler() {
        return new MyAuthenctiationSuccessHandler();
    }

    @Bean
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler() {
        return new MyAuthenctiationFailureHandler();
    }

//    @Bean
//    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
//        return new MyInvocationSecurityMetadataSourceService();
//    }
//
//    @Bean
//    public AccessDecisionManager myAccessDecisionManager() {
//        return new MyAccessDecisionManager();
//    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
