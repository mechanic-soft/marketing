/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午7:52
 */
package cn.com.geasy.marketing.service.article.impl;

import cn.com.geasy.marketing.dao.article.ArticleSharedMapper;
import cn.com.geasy.marketing.domain.entity.article.ArticleShared;
import cn.com.geasy.marketing.service.article.ArticleSharedService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章分享服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class ArticleSharedServiceImpl extends SuperServiceImpl<ArticleSharedMapper, ArticleShared> implements ArticleSharedService {
}
