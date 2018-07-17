/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:37
 */
package cn.com.geasy.marketing.service.wechat;

import cn.com.geasy.marketing.domain.entity.wechat.MpUser;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
public interface MpUserService extends SuperService<MpUser> {
    List<MpUser> findMpUserBySubcriptionUserId(Long userId);
    List<MpUser> findMpUserManager();
}
