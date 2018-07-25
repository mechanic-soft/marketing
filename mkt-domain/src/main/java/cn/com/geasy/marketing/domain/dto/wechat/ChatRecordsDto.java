/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午7:13
 */
package cn.com.geasy.marketing.domain.dto.wechat;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天记录Dto
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecordsDto  implements Serializable {
    private static final long serialVersionUID = 3800368759247477028L;
    /**
     * 聊天记录ID
     */
    private Long id;
    /**
     * 客户id
     */
    private Long customerId;
    /**
     * 微信用户名称
     */
    private String wxUsername;

    /**
     *消息类型
     */
    private Integer msgType;

    /**
     * 内容
     */
    private String content;

    /**
     * 链接地址
     */
    private String url;
    /**
     * 发送状态
     */
    private Integer isSend;


    /**
     *发送时间
     */
    private LocalDateTime sendTime;

    /**
     *状态
     */
    private Integer status;
}