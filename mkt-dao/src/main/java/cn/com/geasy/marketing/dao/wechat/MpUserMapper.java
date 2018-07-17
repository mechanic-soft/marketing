/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:31
 */
package cn.com.geasy.marketing.dao.wechat;

import cn.com.geasy.marketing.domain.entity.wechat.MpUser;
import com.gitee.mechanic.mybatis.base.SuperMapper;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
public interface MpUserMapper extends SuperMapper<MpUser> {
    List<MpUser> findMpUserBySubcriptionUserId(Long userId);
    List<MpUser> findMpUserManager();
}
