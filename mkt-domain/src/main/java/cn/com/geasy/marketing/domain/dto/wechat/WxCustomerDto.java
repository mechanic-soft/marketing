package cn.com.geasy.marketing.domain.dto.wechat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 请在此写下该类的说明
 *
 * @author yml
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxCustomerDto implements Serializable{
    private static final long serialVersionUID = -5854052324159744658L;
    /**
     * 最后一次聊天内容
     */
    private Long id;
    /**
     * 客户Id
     */
    private Long customerId;
    /**
     * 微信号
     */
    private String wxUsername;
    /**
     * 头像URL
     */
    private String headImgUrl;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 消息类型0=文本,1=图片,2=文件)
     */
    private Integer msgType;
    /**
     * 聊天文本内容
     */
    private String content;
    /**
     * 文件/图片URL
     */
    private String url;
    /**
     * 是否发送
     */
    private Integer isSend;
    /**
     * 最后发送信息时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime sendTime;
}
