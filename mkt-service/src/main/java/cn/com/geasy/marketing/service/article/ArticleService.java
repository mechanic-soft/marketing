/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午8:02
 */
package cn.com.geasy.marketing.service.article;

import cn.com.geasy.marketing.domain.dto.article.ArticleDto;
import cn.com.geasy.marketing.domain.entity.article.Article;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

/**
 * 文章服务接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface ArticleService extends SuperService<Article> {
    //Article saveArticle(Article article, Long wechatUserId);

    //Page<ArticleListDto> findByUserIdPage(Page page, String title, Long currentMpUserId);

    Page<ArticleDto> findById(Long id);
}
