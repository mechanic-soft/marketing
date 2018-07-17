/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:18
 */
package cn.com.geasy.marketing.domain.dto.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeAuthAccessToken {
    /**
     * code换取网页授权access_token
     */
    @JsonProperty(value = "access_token")
    private String accessToken;

    /**
     * access_token 有效期
     */
    @JsonProperty(value = "expires_in")
    private String expiresIn;

    /**
     * 刷新access_token
     */
    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    /**
     * 微信的唯一标识openid
     */
    private String openid;

    private String scope;
}
