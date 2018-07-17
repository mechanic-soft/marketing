/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.wechat.impl;

import cn.com.geasy.marketing.dao.wechat.MpUserMapper;
import cn.com.geasy.marketing.domain.entity.wechat.MpUser;
import cn.com.geasy.marketing.service.wechat.MpUserService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class MpUserServiceImpl extends SuperServiceImpl<MpUserMapper, MpUser> implements MpUserService {
    @Override
    public List<MpUser> findMpUserBySubcriptionUserId(Long userId) {
        return baseMapper.findMpUserBySubcriptionUserId(userId);
    }

    @Override
    public List<MpUser> findMpUserManager() {
        return baseMapper.findMpUserManager();
    }
}
