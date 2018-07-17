package cn.com.geasy.marketing.contant;


/**
 * Created by Think on 2018/1/10.
 */
public class Constants {

    /**
     * 服务号API根地址
     */
    private final static String MP_API_URL = "https://api.weixin.qq.com/cgi-bin/";

    public final static String MP_TOKEN_GET = MP_API_URL + "token?grant_type=client_credential&appid={0}&secret={1}";
}
