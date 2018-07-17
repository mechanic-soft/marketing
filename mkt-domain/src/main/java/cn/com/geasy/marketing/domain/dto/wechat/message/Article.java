package cn.com.geasy.marketing.domain.dto.wechat.message;

/**
 * 图文消息具体内容
 * Created by fjh on 2017/7/25.
 */
public class Article {

    /**
     * 图文标题
     */
    private String Title;

    /**
     * 描述语
     */
    private String Description;

    /**
     * 图片
     */
    private String PicUrl;

    /**
     * 链接
     */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Article() {
    }

    public Article(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }
}
