package cn.com.geasy.marketing.domain.dto.wechat.message;


import cn.com.geasy.marketing.contant.MpMessageConstants;

import java.util.List;


/**
 * 图文消息
 * Created by fjh on 2017/7/25.
 */
public class NewsMessage extends BaseMessage {

    /**
     * 图文总数
     */
    private  int ArticleCount;

    /**
     * 图文条目详细消息
     */
    private List<Article> Articles;

    public NewsMessage() {
    }

    /**
     *
     * @param toUser 收件人
     * @param fromUser 发件人
     * @param articles
     */
    public NewsMessage(String toUser, String fromUser,List<Article> articles) {
        this.setToUserName(toUser);
        this.setFromUserName(fromUser);
        this.setMsgType(MpMessageConstants.MSG_TYPE_NEWS);
        this.setCreateTime(System.currentTimeMillis());
        ArticleCount = articles.size();
        Articles = articles;
    }
}
