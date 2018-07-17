/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct.system;

import cn.com.geasy.marketing.domain.dto.system.SysCorpDto;
import cn.com.geasy.marketing.domain.entity.system.SysCorp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * SysCorp 与 SysCorpDto 互转
 *
 * @author phil
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysCorpMapstruct {
    SysCorpMapstruct getInstance = Mappers.getMapper(SysCorpMapstruct.class);

    SysCorp toEntity(SysCorpDto dto);

    List<SysCorp> toEntityList(List<SysCorpDto> dtoList);

    SysCorpDto toDto(SysCorp domain);

    List<SysCorpDto> toDtoList(List<SysCorp> domainList);
}
