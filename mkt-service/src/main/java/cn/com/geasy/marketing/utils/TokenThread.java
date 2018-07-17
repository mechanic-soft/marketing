package cn.com.geasy.marketing.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Think on 2018/1/15.
 */
@Slf4j
public class TokenThread implements Runnable {

    private  String appId;

    private String secrect;

    public TokenThread() {
    }

    public TokenThread(String appId, String secrect) {
        this.appId = appId;
        this.secrect = secrect;
    }

    @Override
    public void run() {
        while (true) {
            try {
                  String accessToken=MpServiceApi.getAccessToken(appId,secrect);
                    // 如果access_token为null，60秒后再获取
                    MpTokenUtil.token = accessToken;
                    log.info("appId is :{},appSecret is :{}, accessToken is :{} ",appId,secrect,accessToken);
                    Thread.sleep(60*60 * 1000);
            } catch (InterruptedException e) {

            }
    }
}
}
