/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct.system;

import cn.com.geasy.marketing.domain.dto.system.SysMenuDto;
import cn.com.geasy.marketing.domain.entity.system.SysMenu;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * SysMenu 与 SysMenuDto 互转
 *
 * @author phil
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysMenuMapstruct {
    SysMenuMapstruct getInstance = Mappers.getMapper(SysMenuMapstruct.class);

    SysMenu toEntity(SysMenuDto dto);

    List<SysMenu> toEntityList(List<SysMenuDto> dtoList);

    SysMenuDto toDto(SysMenu domain);

    List<SysMenuDto> toDtoList(List<SysMenu> domainList);
}
