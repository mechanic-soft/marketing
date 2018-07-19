///*
// * Copyright 2016-2018 the original author or authors.
// * Created on 2018/7/18 下午4:00
// */
//package cn.com.geasy.marketing.service.shiro;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.web.filter.mgt.FilterChainManager;
//import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
//import org.apache.shiro.web.util.WebUtils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.util.Iterator;
//
///**
// * 请在此写下该类的说明
// *
// * @author phil
// * @version 1.0.0
// */
//@Slf4j
//public class RestPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {
//    public RestPathMatchingFilterChainResolver() {
//        super();
//    }
//
//    public RestPathMatchingFilterChainResolver(FilterConfig filterConfig) {
//        super(filterConfig);
//    }
//
//    /**
//     * 重写filterChain匹配
//     * @param request ServletRequest
//     * @param response ServletResponse
//     * @param originalChain FilterChain
//     * @return javax.servlet.FilterChain
//     */
//    @Override
//    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
//        FilterChainManager filterChainManager = this.getFilterChainManager();
//        if (!filterChainManager.hasChains()) {
//            return null;
//        } else {
//            String requestURI = this.getPathWithinApplication(request);
//            Iterator var6 = filterChainManager.getChainNames().iterator();
//
//            String pathPattern;
//            boolean flag = true;
//            String[] strings = null;
//            do {
//                if (!var6.hasNext()) {
//                    return null;
//                }
//
//                pathPattern = (String)var6.next();
//
//                strings = pathPattern.split("==");
//                if (strings.length == 2) {
//                    // 分割出url+httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
//                    if (WebUtils.toHttp(request).getMethod().toUpperCase().equals(strings[1].toUpperCase())) {
//                        flag = false;
//                    } else {
//                        flag = true;
//                    }
//                } else {
//                    flag = false;
//                }
//                pathPattern = strings[0];
//            } while(!this.pathMatches(pathPattern, requestURI) || flag);
//
//            if (log.isTraceEnabled()) {
//                log.trace("Matched path pattern [" + pathPattern + "] for requestURI [" + requestURI + "].  Utilizing corresponding filter chain...");
//            }
//            if (strings.length == 2) {
//                pathPattern = pathPattern.concat("==").concat(WebUtils.toHttp(request).getMethod().toUpperCase());
//            }
//
//            return filterChainManager.proxy(originalChain, pathPattern);
//        }
//    }
//}
