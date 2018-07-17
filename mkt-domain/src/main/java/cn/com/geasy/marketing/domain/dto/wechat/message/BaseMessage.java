package cn.com.geasy.marketing.domain.dto.wechat.message;

/**
 * Created by fjh on 2017/7/25.
 */
public class BaseMessage {

    /**
     * 收件人
     */
    private String ToUserName;


    /**
     * 发件人
     */
    private String FromUserName;


    /**
     * 发送时间
     */
    private Long CreateTime;

    /**
     * 发送类型
     */
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
