/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct.system;

import cn.com.geasy.marketing.domain.dto.system.SysPermissionDto;
import cn.com.geasy.marketing.domain.entity.system.SysPermission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * SysPermission 与 SysPermissionDto 互转
 *
 * @author phil
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPermissionMapstruct {
    SysPermissionMapstruct getInstance = Mappers.getMapper(SysPermissionMapstruct.class);

    SysPermission toEntity(SysPermissionDto dto);

    List<SysPermission> toEntityList(List<SysPermissionDto> dtoList);

    SysPermissionDto toDto(SysPermission domain);

    List<SysPermissionDto> toDtoList(List<SysPermission> domainList);
}
