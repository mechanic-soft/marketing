///*
// * Copyright 2016-2018 the original author or authors.
// * Created on 2018/7/18 下午4:13
// */
//package cn.com.geasy.marketing.service.shiro;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.PathMatchingFilter;
//import org.apache.shiro.web.util.WebUtils;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
///**
// * 请在此写下该类的说明
// *
// * @author phil
// * @version 1.0.0
// */
//public abstract class RestPathMatchingFilter extends PathMatchingFilter {
//    public RestPathMatchingFilter() { }
//
//    /* *
//     * @Description 重写URL匹配  加入httpMethod支持
//     * @Param [path, request]
//     * @Return boolean
//     */
//    @Override
//    protected boolean pathsMatch(String path, ServletRequest request) {
//        String requestURI = this.getPathWithinApplication(request);
//        // path: url==method eg: http://api/menu==GET   需要解析出path中的url和httpMethod
//        String[] strings = path.split("==");
//        if (strings.length <= 1) {
//            // 分割出来只有URL
//            return this.pathsMatch(strings[0], requestURI);
//        } else {
//            // 分割出url+httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
//            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();
//            return httpMethod.equals(strings[1].toUpperCase()) && this.pathsMatch(strings[0], requestURI);
//        }
//    }
//
//
//    protected Subject getSubject(ServletRequest request, ServletResponse response) {
//        return SecurityUtils.getSubject();
//    }
//
//    protected abstract boolean isAccessAllowed(ServletRequest var1, ServletResponse var2, Object var3) throws Exception;
//
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        return this.onAccessDenied(request, response);
//    }
//
//    protected abstract boolean onAccessDenied(ServletRequest var1, ServletResponse var2) throws Exception;
//
//    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        return this.isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
//    }
//
//
//
//    protected void saveRequest(ServletRequest request) {
//        WebUtils.saveRequest(request);
//    }
//
//
//
//}
