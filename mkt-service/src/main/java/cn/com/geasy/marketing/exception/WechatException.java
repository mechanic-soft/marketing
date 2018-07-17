/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 上午10:45
 */
package cn.com.geasy.marketing.exception;

import com.gitee.mechanic.core.enums.HttpCode;
import com.gitee.mechanic.core.exception.SuperRuntimeException;

/**
 * 微信异常
 *
 * @author phil
 * @version 1.0.0
 */
public class WechatException extends SuperRuntimeException {

    private static final long serialVersionUID = -6357149550353160810L;

    public WechatException(HttpCode httpCode) {
        super(httpCode);
    }

    public WechatException(HttpCode httpCode, String message ) {
        super(httpCode, message);
    }
}
