/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct.system;

import cn.com.geasy.marketing.domain.dto.system.SysLogDto;
import cn.com.geasy.marketing.domain.entity.system.SysLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * SysLog 与 SysLogDto 互转
 *
 * @author phil
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysLogMapstruct {
    SysLogMapstruct getInstance = Mappers.getMapper(SysLogMapstruct.class);

    SysLog toEntity(SysLogDto dto);

    List<SysLog> toEntityList(List<SysLogDto> dtoList);

    SysLogDto toDto(SysLog domain);

    List<SysLogDto> toDtoList(List<SysLog> domainList);
}
