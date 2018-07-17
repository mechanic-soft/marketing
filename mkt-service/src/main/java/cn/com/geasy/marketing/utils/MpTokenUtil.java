/*
 * Copyright 2016-2017 TVI Go Easy.
 * Created on 2017/6/12 09:09
 */
package cn.com.geasy.marketing.utils;

import cn.com.geasy.marketing.config.property.MpInfo;
import cn.com.geasy.marketing.contant.Constants;
import jodd.http.HttpUtil;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;

/**
 * 微信公众号Token工具类
 *
 * @author mechanic
 * @version 0.0.1
 */
@Component
public class MpTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(MpTokenUtil.class);

    @Autowired
    private MpInfo mpInfo;

    public static  String token="";


    public  String getToken() {
        if (StringUtil.isEmpty(token)) {
            return flush();
        }
        return token;
    }
    /**
     * 刷新token
     * @return
     */
    public  String flush() {
        logger.debug("刷新Token...");
        return getSetMpToken( mpInfo.getAppId(), mpInfo.getAppSecret());
    }


    /**
     * 获取服务号 token
     *
     * @param mpId 服务号ID
     * @param secret 服务号密钥
     * @return 服务号token字符串
     */
    public  String getSetMpToken(String mpId, String secret) {
        logger.debug("通过appId和Secret 微信请求Token...");
        String tokenUrl = MessageFormat.format(
          Constants.MP_TOKEN_GET,
          mpId,
          secret
        );
        Map mapValue = HttpUtils.commonGet(tokenUrl);
        token= mapValue.get("access_token").toString();
        logger.debug("获取token的 appId is :{} ,secret is :{}",mpId,secret);
        logger.debug("新的token：" + token);
        return token;
    }
}
