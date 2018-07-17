/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 上午10:42
 */
package cn.com.geasy.marketing.utils;

import cn.com.geasy.marketing.domain.entity.wechat.MpUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
public class SessionUtils {
    public static HttpSession getSession() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest=attrs.getRequest();
        return  httpServletRequest.getSession();
    }

    /**
     * 在session 设置当前微信用户信息
     * @param mpUser
     */
    public static void setCurrentMpUser(MpUser mpUser) {
        HttpSession session=getSession();
        session.setAttribute("mpUser",mpUser);
    }

    /**
     * 在session 获取当前用户信息
     * @return
     */
    public static MpUser getCurrentMpUser() {
        HttpSession session=getSession();
        MpUser mpUser= (MpUser) session.getAttribute("mpUser");
        return mpUser;
    }

    /**
     * 获取当前微信用户id
     * @return
     */
    public static Long getCurrentMpUserId() {
        HttpSession session=getSession();
        MpUser mpUser= (MpUser) session.getAttribute("mpUser");
        if (mpUser != null) {
            return mpUser.getId();
        }
        return null;
    }
}
