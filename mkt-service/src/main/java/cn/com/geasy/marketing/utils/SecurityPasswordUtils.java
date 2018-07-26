/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/26 下午12:09
 */
package cn.com.geasy.marketing.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring security 密码处理工具类
 *
 * @author phil
 * @version 1.0.0
 */
public class SecurityPasswordUtils {
    /**
     * 密码加密
     * @param password 明文密码
     * @return String
     */
    public static String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
