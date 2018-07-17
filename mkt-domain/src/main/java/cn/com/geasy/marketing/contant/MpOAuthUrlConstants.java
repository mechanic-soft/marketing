package cn.com.geasy.marketing.contant;

/**
 * 微信服务号网页授权的url
 * Created by fjh on 2017/7/18.
 */

public class MpOAuthUrlConstants {

    /**
     *微信服务号网页默认授权只获取到openid
     */
    public  static final String  OAUTH_SNSAPI_BASE_URL="https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    /**
     * 微信服务号网页授权获取用户微信个人信息
     */
    public  static final String OAUTH_SNSAPI_USERINFO_URL="https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
}
