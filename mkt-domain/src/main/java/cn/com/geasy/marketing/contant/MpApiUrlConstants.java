package cn.com.geasy.marketing.contant;

/**
 *
 * 微信公众号api 接口请求路径
 * Created by fjh on 2017/7/18.
 */
public class MpApiUrlConstants {


    /**
     *获取服务号的access_token的请求路径access_token是公众号的全局唯一接口调用凭据
     */
    public  static  final String MP_GET_TOKEN_URL=
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 微信服务号获取网页授权access_token 的请求路径，http GET方式  通过code换取网页授权access_token
     */
    public static final String MP_ACCESS_TOKEN_BY_WEB_OAUTH_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 通过access_token和openid拉取用户信息的请求路径，http：GET
     */
    public static final String MP_GET_USER_INFO_BY_WEB_OAUTH_URL =
            "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 微信服务号获取jssdk的 jsapi_ticket的微信请求路径，http GET方式请求获得jsapi_ticket
     */
    public static final String MP_JSAPI_TICKET_URL=
            "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    /**
     * 开发者可通过OpenID来获取用户基本信息
     * http: GET
     */
    public static final String MP_USER_INFO_URL=
            "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN ";

    /**
     * 自定义创建微信服务号菜单
     */
    public static final String MP_CREATE_MENU_URL=
            " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 发送模板消息
     */
    public static final String MP_TEMPLATE_MESSAGE_URL=
            "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    /**
     * 创建二维码
     */
    public static final String MP_QRCODE_CREATE_URL=
            "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST";

}
