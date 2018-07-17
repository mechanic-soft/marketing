/*
 * Copyright 2016-2017 TVI Go Easy.
 * Created on 2017/4/29 23:00
 */
package cn.com.geasy.marketing.utils;

import cn.com.geasy.marketing.exception.WechatException;
import com.fasterxml.jackson.databind.JavaType;
import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.ServiceException;
import com.gitee.mechanic.json.mapper.JsonMapper;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Http 交互工具类
 *
 * @author mechanic
 * @version 0.0.1
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static final String MEDIA_TYPE_JSON_UTF8 = "application/json;charset=UTF-8";

    private static String SEPARATOR = "access_token=";

    private static JsonMapper jsonMapper;

    static {
        jsonMapper = new JsonMapper();
    }

    /**
     * 发送 POST 请求
     *
     * @param url      需请求的 URL 地址
     * @param bodyText 请求发送的参数
     * @return 请求返回的结果
     */
    public static Map<String, Object> post(String url, String bodyText) {
        logger.debug("Post Request URL: {} and body is {}" ,url,bodyText);

        HttpResponse response = HttpRequest.post(url).bodyText(bodyText, MEDIA_TYPE_JSON_UTF8).send();
        String body = null;
        try {
            body = new String(response.bodyBytes(), "UTF-8");
            logger.debug("post result is :{}",body);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(HttpCode.CODING_ERROR);
        }
        Map<String, Object> returnMessageMap = getReturnMessageMap(body);


        return processReturnMessage(returnMessageMap);
    }

    /**
     * 发送 GET 请求
     *
     * @param url 需请求的 URL 地址
     * @return 请求返回的结果
     */
    public static Map<String, Object> get(String url) {

        logger.debug("Get Request URL: \n" + url);

        HttpResponse response = HttpRequest.get(url).send();
        String body = null;
        try {
            body = new String(response.bodyBytes(), "UTF-8");
            logger.debug("post result is :{}",body);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(HttpCode.CODING_ERROR);
        }

        Map<String, Object> returnMessageMap = getReturnMessageMap(body);

        return processReturnMessage(returnMessageMap);
    }

    /**
     * 发送 GET 请求 不校验token是否有效
     *
     * @param url 需请求的 URL 地址
     * @return 请求返回的结果
     */
    public static Map<String, Object> commonGet(String url) {

        logger.debug("Get Request URL: \n" + url);

        HttpResponse response = HttpRequest.get(url).send();
        String body = null;
        try {
            body = new String(response.bodyBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(HttpCode.CODING_ERROR);
        }
        Map<String, Object> returnMessageMap = getReturnMessageMap(body);
        return processReturnMessage(returnMessageMap);
    }

    /**
     * 获取当前请求中的根 URL
     *
     * @param request HttpServletRequest
     * @return URL
     */
    public static String getRootURL(HttpServletRequest request) {
        return getRootURLasStringBuffer(request).toString();
    }

    /**
     * 获取当前请求中的带上下文路径的 URL
     *
     * @param request HttpServletRequest
     * @return URL
     */
    public static String getContextURL(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return getRootURL(request) + contextPath;
    }

    /**
     * 获取当前请求中的不包含协议的 URL
     *
     * @param request HttpServletRequest
     * @return URL
     */
    public static String getNoSchemeContextURL(HttpServletRequest request) {
        return request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /**
     * 将微信返回的Json信息转换为Map
     *
     * @param message 微信返回的Json信息
     * @return map
     */
    private static Map<String, Object> getReturnMessageMap(String message) {
        if (message == null) {
            throw new WechatException(HttpCode.WX_NO_CONTENT, "微信 API 接口没有返回值，通常是 API 地址错误导致。");
        }

        JavaType javaType = jsonMapper.buildType(Map.class, String.class, Object.class);
        return jsonMapper.fromJson(message, javaType);
    }

    /**
     * 根据微信返回信息判断请求中的Token是否失效，如果失效会刷新token，并返回false
     *
     * @param returnMessageMap 微信返回信息
     * @return 失效时返回false，有效返回true
     */
    private static boolean isValidToken(Map<String, Object> returnMessageMap) {
        if (!returnMessageMap.containsKey("errcode")) {
            return true;
        }

        HttpCode httpCode = convertCode(returnMessageMap.get("errcode"));

        return !(httpCode == HttpCode.WX_42001);
                 /*|| httpCode == HttpCode.WX_42002
                 || httpCode == HttpCode.WX_40014
                 || httpCode == HttpCode.WX_40091
                 || httpCode == HttpCode.WX_42009
                 || httpCode == HttpCode.WX_40001);*/
    }

    /**
     * 处理微信返回信息。如返回从code不是0则抛出异常，否则不做处理。
     *
     * @param returnMessageMap 微信返回信息
     * @return 微信返回信息
     */
    private static Map<String, Object> processReturnMessage(Map<String, Object> returnMessageMap) {
        if (returnMessageMap.containsKey("errcode")) {
            logger.debug("errcode: " + returnMessageMap.get("errcode").toString());
            logger.debug("errmsg: " + returnMessageMap.get("errmsg").toString());

            HttpCode httpCode = convertCode(returnMessageMap.get("errcode"));

            if (httpCode != HttpCode.WX_OK) {
                throw new WechatException(httpCode);
            }
        }
        return returnMessageMap;
    }

    /**
     * 将一些特殊的返回码进行转换
     *
     * @param wxErrorCode 微信返回码
     * @return HttpCode
     */
    private static HttpCode convertCode(Object wxErrorCode) {
        if (wxErrorCode == null) {
            throw new NullPointerException("微信返回码不能为空");
        }
        String code = "WX_";

        switch (wxErrorCode.toString()) {
            case "0":
                code += "OK";
                break;
            case "-1":
                code += "SYSTEM_BUSY";
                break;
            case "204":
                code += "NO_CONTENT";
                break;
            case "4000":
                code += "MESSAGE_RESOLVE_ERROR";
                break;
            default:
                code += wxErrorCode;
                break;
        }
        return HttpCode.valueOf(code);
    }

    /**
     * 获取当前请求中的根 URL
     *
     * @param request HttpServletRequest
     * @return URL
     */
    private static StringBuffer getRootURLasStringBuffer(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        StringBuffer requestURL = request.getRequestURL();
        return requestURL.delete(requestURL.indexOf(contextPath), requestURL.length());
    }




    public static String replaceQueryParameter(String url, String name, String value) {
        if(StringUtils.isNotBlank(url)) {
            url = url.replaceAll("(" + name +"=[^&]*)", name + "=" + value);
        }
        return url;
    }

    public static void main (String[] args){

        String urlString = "http://localhost/user/getuserinfo?abc=22&access_token={0}&code={1}";
        String urlString2 = "http://localhost/user/getuserinfo?access_token={0}&code={1}";
        String urlString3 = "http://localhost/user/getuserinfo?code={1}";

        System.out.println(HttpUtils.replaceQueryParameter(urlString,"access_token", "hndfsy8uoifakjs"));
        System.out.println(HttpUtils.replaceQueryParameter(urlString2,"access_token", "hndfsy8uoifakjs"));
        System.out.println(HttpUtils.replaceQueryParameter(urlString3,"access_token", "hndfsy8uoifakjs"));
    }
}
