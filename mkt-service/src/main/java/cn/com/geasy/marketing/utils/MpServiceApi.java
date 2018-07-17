package cn.com.geasy.marketing.utils;


import cn.com.geasy.marketing.contant.MpApiUrlConstants;
import cn.com.geasy.marketing.domain.dto.wechat.CodeAuthAccessToken;
import cn.com.geasy.marketing.domain.dto.wechat.Menu.Menu;
import cn.com.geasy.marketing.domain.dto.wechat.QrcodeInfo;
import cn.com.geasy.marketing.domain.entity.wechat.MpUser;
import com.gitee.mechanic.json.mapper.JsonMapper;

import java.util.Map;


/**
 * Created by fjh on 2017/7/18.
 */
public class MpServiceApi {
    private static JsonMapper jsonMapper;

    static {
        jsonMapper = new JsonMapper();
    }


    /**
     * 获取access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token
     *
     * @param appid
     * @param secret
     * @return
     */
    public static String getAccessToken(String appid, String secret) {
        String url = MpApiUrlConstants.MP_GET_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", secret);
        Map map = HttpUtils.get(url);
        return map.get("access_token").toString();
    }

    /**
     * 这里通过code换取的是一个特殊的网页授权access_token
     *
     * @param appid
     * @param secret
     * @param code
     * @return
     */
    public static CodeAuthAccessToken AuthAccessToken(String appid, String secret, String code) {
        String url = MpApiUrlConstants.MP_ACCESS_TOKEN_BY_WEB_OAUTH_URL
                .replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        Map map = HttpUtils.get(url);
//        JSONObject jsonObject = new JSONObject(map);
//        CodeAuthAccessToken codeAuthAccessToken = JSONObject.parseObject(jsonObject.toString(), CodeAuthAccessToken.class);
        String content = jsonMapper.toJson(map);
        return jsonMapper.fromJson(content, CodeAuthAccessToken.class);
    }

    /**
     * 通过access_token和openid拉取用户信息了
     *
     * @param token
     * @param openid
     * @return
     */
    public static MpUser getUserInfoByWebOAuth(String token, String openid) {
        String url = MpApiUrlConstants.MP_GET_USER_INFO_BY_WEB_OAUTH_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
        Map map = HttpUtils.get(url);
//        JSONObject jsonObject = new JSONObject(map);
//        WechatUserInfo wechatUserInfo = JSONObject.parseObject(jsonObject.toString(), WechatUserInfo.class);


        String content = jsonMapper.toJson(map);
        return jsonMapper.fromJson(content, MpUser.class);

        //JsonMapper.defaultMapper().fromJson(jsonObject.toString(),WechatUserInfo.class);
//        return wechatUserInfo;
    }

    /**
     * 网页授权code 获取用户信息
     *
     * @param appid
     * @param secret
     * @param code
     * @return
     */
    public static MpUser getUserInfoByWebOAuth(String appid, String secret, String code) {

        CodeAuthAccessToken codeAuthAccessToken = AuthAccessToken(appid, secret, code);
        if (codeAuthAccessToken == null) {

            return null;
        }
        return getUserInfoByWebOAuth(codeAuthAccessToken.getAccessToken(), codeAuthAccessToken.getOpenid());
    }

    /**
     * 用第一步拿到的access_token 采用http GET方式请求获得jsapi_ticket
     *
     * @param token
     */
    public static String getJsapiTicket(String token) {
        String url = MpApiUrlConstants.MP_JSAPI_TICKET_URL.replace("ACCESS_TOKEN", token);
        Map map = HttpUtils.get(url);
        return map.get("ticket").toString();
    }


    /**
     * 开发者可通过OpenID来获取用户基本信息
     *
     * @param openId
     * @param token
     * @return
     */
    public static MpUser getUserInfoByOpenId(String openId, String token) {
        String url =
                MpApiUrlConstants.MP_USER_INFO_URL.replace("ACCESS_TOKEN", token).replace("OPENID", openId);
        Map map = HttpUtils.get(url);
//        JSONObject jsonObject = new JSONObject(map);
//        WechatUserInfo wechatUserInfo = JSONObject.parseObject(jsonObject.toString(), WechatUserInfo.class);
//        return wechatUserInfo;
        String content = jsonMapper.toJson(map);
        return jsonMapper.fromJson(content, MpUser.class);
    }

    /**
     * 自定义创建菜单
     *
     * @param token
     * @param menu
     * @return
     */
    public static Map createMenu(String token, Menu menu) {
        String menuText = jsonMapper.toJson(menu);
        String url = MpApiUrlConstants.MP_CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        Map map = HttpUtils.post(url, menuText);
        return map;
    }

    /**
     * 发生模板消息
     *
     * @param token
     * @param dataText
     * @return
     */
    public static Map sendTemplateMessage(String token, String dataText) {
        String url = MpApiUrlConstants.MP_TEMPLATE_MESSAGE_URL.replace("ACCESS_TOKEN", token);
        Map map = null;
        try {
            map = HttpUtils.post(url, dataText);
        } catch (Exception e) {
        }
        return map;
    }

    public static QrcodeInfo getMpQRcode(String sceneStr, String token) {
        String url = MpApiUrlConstants.MP_QRCODE_CREATE_URL.replace("TOKENPOST", token);
        String text = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + sceneStr + "\"}}}";
        Map map = HttpUtils.post(url, text);
//        JSONObject jsonObject = new JSONObject(map);
//        QrcodeInfo qrcodeInfo = JSONObject.parseObject(jsonObject.toString(), QrcodeInfo.class);
        String content = jsonMapper.toJson(map);
        return jsonMapper.fromJson(content, QrcodeInfo.class);
    }


}