package cn.com.geasy.marketing.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * url 域名
 * Created by Think on 2017/8/2.
 */
public class DomainUtils {

    /**
     * api域名路径
     * @param request
     * @param servletUrl
     * @return
     */
    public static  String getServerNameBaseUrl(HttpServletRequest request, String servletUrl) {
        return request.getScheme()+"://"+request.getServerName()+request.getContextPath()+servletUrl;
    }

    /**
     * 前端页面的域名路径
     * @param request
     * @param pageUrl
     * @return
     */
    public static String getServerNameBaseurlNotContextPath(HttpServletRequest request, String pageUrl) {
        return request.getScheme()+"://"+request.getServerName()+pageUrl;
    }

    /**
     * 获取api域名
     * @return
     */
    public static String domainBaseUrl() {
        HttpServletRequest request=getHttpServletRequest();
        return request.getScheme()+"://"+request.getServerName()+request.getContextPath();
    }

    /**
     * 获取httpServletRequest
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest=attrs.getRequest();
        return httpServletRequest;
    }
}
