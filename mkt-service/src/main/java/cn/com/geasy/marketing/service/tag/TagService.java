/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:11
 */
package cn.com.geasy.marketing.service.tag;

import cn.com.geasy.marketing.domain.entity.tag.Tag;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
public interface TagService extends SuperService<Tag> {
    List<Tag> findTagByArticleId(Long articleId);
}
