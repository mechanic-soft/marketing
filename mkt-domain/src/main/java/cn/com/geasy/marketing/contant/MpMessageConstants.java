/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 上午11:03
 */
package cn.com.geasy.marketing.contant;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
public class MpMessageConstants {
    /**
     * 关注
     */
    public static final String SUBSCRIBE = "subscribe";

    /**
     * 微信接收事件
     */
    public static final String EVENT = "event";

    /**
     * 自定义菜单事件 click
     */
    public static final String CLICK = "CLICK";

    /**
     * 自定义菜单事件 view
     */
    public static final String VIEW = "VIEW";

    /**
     * 无消息回复 success
     */
    public static final String SUCCESS ="success";

    /**
     * 回复文本消息类型
     */
    public static final String MSG_TYPE_TEXT = "text";

    /**
     * 回复图文消息类型
     */
    public static final String MSG_TYPE_NEWS = "news";

    /**
     * 模板消息开始 first
     */
    public static final String MTP_FIRST = "first";

    /**
     * 模板消息最后的描述 remark
     */
    public static final String MTP_REMARK = "remark";

    /**
     * 模板消息 keyword
     */
    public static final String MTP_KEYWORD = "keyword";

    /**
     * 模板消息的接收人
     */
    public static final String MTP_TOUSER = "touser";

    /**
     * 模板消息 template_id
     */
    public static final String MTP_TEMPLATE_ID = "template_id";

    /**
     * 模板消息 url
     */
    public static final String MTP_URL ="url";

    /**
     * 模板消息 value
     */
    public static final String MTP_VALUE = "value";

    /**
     * 模板消息 data
     */
    public static final String MTP_DATA = "data";

    /**
     * 被动回复的文本消息
     */
    public static final String TextMessage = "<xml>" +
            "<ToUserName><![CDATA[toUser]]></ToUserName>" +
            "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
            "<CreateTime>12345678</CreateTime>" +
            "<MsgType><![CDATA[text]]></MsgType>" +
            "<Content><![CDATA[CONTENT]]></Content>" +
            "</xml>";
}
