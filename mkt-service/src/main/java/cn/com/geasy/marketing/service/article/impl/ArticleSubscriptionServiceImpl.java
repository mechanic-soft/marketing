/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.article.impl;

import cn.com.geasy.marketing.dao.article.ArticleSubscriptionMapper;
import cn.com.geasy.marketing.domain.entity.article.ArticleSubscription;
import cn.com.geasy.marketing.service.article.ArticleSubscriptionService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章阅读表ServiceImpl
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class ArticleSubscriptionServiceImpl extends SuperServiceImpl<ArticleSubscriptionMapper, ArticleSubscription> implements ArticleSubscriptionService {

}
