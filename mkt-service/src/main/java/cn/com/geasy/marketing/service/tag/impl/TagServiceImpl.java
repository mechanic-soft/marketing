/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.tag.impl;

import cn.com.geasy.marketing.dao.tag.TagMapper;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.service.tag.TagService;
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
public class TagServiceImpl extends SuperServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public List<Tag> findTagByArticleId(Long articleId) {
        return baseMapper.findTagByArticleId(articleId);
    }
}
