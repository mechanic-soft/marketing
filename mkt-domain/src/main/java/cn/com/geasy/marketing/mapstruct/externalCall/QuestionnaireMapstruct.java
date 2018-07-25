/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct.externalCall;

import cn.com.geasy.marketing.domain.dto.externalCall.QuestionnaireDto;
import cn.com.geasy.marketing.domain.entity.externalCall.Questionnaire;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Questionnaire 与 QuestionnaireDto 互转
 *
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public interface QuestionnaireMapstruct {
    QuestionnaireMapstruct getInstance = Mappers.getMapper(QuestionnaireMapstruct.class);

    Questionnaire toEntity(QuestionnaireDto dto);

    List<Questionnaire> toEntityList(List<QuestionnaireDto> dtoList);

    QuestionnaireDto toDto(Questionnaire domain);

    List<QuestionnaireDto> toDtoList(List<Questionnaire> domainList);
}
