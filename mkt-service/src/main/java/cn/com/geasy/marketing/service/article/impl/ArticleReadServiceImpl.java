/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/16 下午8:38
 */
package cn.com.geasy.marketing.service.article.impl;

import cn.com.geasy.marketing.dao.article.ArticleReadMapper;
import cn.com.geasy.marketing.domain.entity.article.ArticleRead;
import cn.com.geasy.marketing.service.article.ArticleReadService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章阅读表ServiceImpl
 *
 * @author gencheng.pan
 * @version 1.0.0
 */
@Service
public class ArticleReadServiceImpl extends SuperServiceImpl<ArticleReadMapper, ArticleRead> implements ArticleReadService {

}
