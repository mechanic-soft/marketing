/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午6:04
 */
package cn.com.geasy.marketing.dao.article;

import cn.com.geasy.marketing.domain.entity.article.Article;
import com.gitee.mechanic.mybatis.base.SuperMapper;

/**
 * 文章数据访问接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface ArticleMapper extends SuperMapper<Article> {
    //List<Article> findByUserIdPage(@Param("title")String title, @Param("userId") Long userId);

//    List<ArticleHotDto> findByHotArticlePage();

    //List<Article> findBySubscribePage(@Param("userId") Long userId);

    //List<Article> findByLikePage(@Param("userId") Long userId);

//    List<ArticleReadRecordDto> findRecentReadRecord(@Param("userId") Long userId);
}
