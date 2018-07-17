package cn.com.geasy.marketing.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Think on 2018/1/12.
 */
@Component
@ConfigurationProperties(prefix="mp")
@Data
public class MpInfo {

    private String appId;

    private String appSecret;

    private String appToken;

    /**
     * 公众号二维码
     */
    private String qrcode;
}
