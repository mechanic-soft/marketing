package cn.com.geasy.marketing.utils;

import cn.com.geasy.marketing.contant.MpJssdkConstants;
import com.gitee.mechanic.core.utils.EncodeUtil;
import com.gitee.mechanic.core.utils.HashUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * 服务号jssdk 签名
 * Created by fjh on 2017/7/18.
 */
public class MpJssdkSignatureApi {

    public static void main(String[] args) {
        String jsapi_ticket = "jsapi_ticket";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, Object> ret = sign(jsapi_ticket, url,"");
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };

    /**
     * 获取jssdk 的签名
     * @param url
     * @return
     */
    public static Map<String, Object> getSignature(String url, String token,String appid) {
        String ticket=MpServiceApi.getJsapiTicket(token);
        return sign(ticket,url,appid);
    }

    /**
     *
     * 所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，
     * 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1。
     * 这里需要注意的是所有参数名均为小写字符。对string1作sha1加密
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public static Map<String, Object> sign(String jsapi_ticket, String url,String appid) {
        Map<String, Object> ret = new HashMap<String, Object>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);
        signature = EncodeUtil.encodeHex(HashUtil.sha1(string1));
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId",appid);
        ret.put("jsApiList", MpJssdkConstants.jsApiList);
        return ret;
    }

    /**
     * 获取随机数
     * @return
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 时间戳
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
