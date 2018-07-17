package cn.com.geasy.marketing.domain.dto.wechat.message;


import cn.com.geasy.marketing.contant.MpMessageConstants;

/**
 * 文本消息
 * Created by fjh on 2017/7/25.
 */
public class TextMessage extends BaseMessage {

    /**
     * 文本消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    /**
     * 实例化 设置默认值
     */
    public TextMessage() {
        this.setMsgType(MpMessageConstants.MSG_TYPE_TEXT);
        this.setCreateTime(System.currentTimeMillis());
    }

    /**
     * 实例化设置默认值
     * @param toUser 收件人
     * @param fromUser 发件人
     * @param content
     */
    public TextMessage(String toUser , String fromUser, String content) {
        this.setMsgType(MpMessageConstants.MSG_TYPE_TEXT);
        this.setCreateTime(System.currentTimeMillis());
        this.setToUserName(toUser);
        this.setFromUserName(fromUser);
        this.setContent(content);
    }

}
