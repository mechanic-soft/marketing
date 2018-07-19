/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/19 下午2:04
 */
package cn.com.geasy.marketing.config;

import cn.com.geasy.marketing.security.MyAccessDecisionManager;
import cn.com.geasy.marketing.security.MyInvocationSecurityMetadataSourceService;
import cn.com.geasy.marketing.service.security.SecurityUserService;
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

import java.io.PrintWriter;

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
        return new SecurityUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService()).passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .accessDecisionManager()
//
////                .antMatchers(HttpMethod.GET, "/sys/users").hasRole("ADMIN")
////                .antMatchers(HttpMethod.POST, "/sys/users").hasRole("ADMIN")
////                .antMatchers(HttpMethod.GET, "/sys/users/**").hasRole("USER")
//                .anyRequest().permitAll()
////                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/unauthor")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
////                        String message = ResponseUtils.result("用户【"  + authentication.getName() + "】登录成功").toString();
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("{\"status\":\"ok\",\"msg\":\"登录成功\"}");
////                        out.write(message);
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        e.printStackTrace();
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll()
//                .and().logout().permitAll().and().csrf().disable();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O filterSecurityInterceptor) {
                        filterSecurityInterceptor.setSecurityMetadataSource(mySecurityMetadataSource());
                        filterSecurityInterceptor.setAccessDecisionManager(myAccessDecisionManager());
                        return filterSecurityInterceptor;
                    }
                })

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl("/login?error")
                .loginPage("/unauthor")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                        String message = ResponseUtils.result("用户【"  + authentication.getName() + "】登录成功").toString();
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"status\":\"ok\",\"msg\":\"登录成功\"}");
//                        out.write(message);
                    out.flush();
                    out.close();
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    e.printStackTrace();
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                    out.flush();
                    out.close();
                })
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and().logout().permitAll().and().csrf().disable();
    }


    @Bean
    public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
        MyInvocationSecurityMetadataSourceService securityMetadataSource = new MyInvocationSecurityMetadataSourceService();
        return securityMetadataSource;
    }

    @Bean
    public AccessDecisionManager myAccessDecisionManager() {
        return new MyAccessDecisionManager();
    }
}
