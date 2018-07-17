/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct.article;

import cn.com.geasy.marketing.domain.dto.article.ArticleDto;
import cn.com.geasy.marketing.domain.entity.article.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Article 与 ArticleDto 互转
 *
 * @author phil
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapstruct {
    ArticleMapstruct getInstance = Mappers.getMapper(ArticleMapstruct.class);

    Article toEntity(ArticleDto dto);

    List<Article> toEntityList(List<ArticleDto> dtoList);

    ArticleDto toDto(Article domain);

    List<ArticleDto> toDtoList(List<Article> domainList);
}
