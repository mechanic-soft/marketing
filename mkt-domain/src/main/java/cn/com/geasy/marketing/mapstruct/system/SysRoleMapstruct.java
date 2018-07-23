/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct.system;

import cn.com.geasy.marketing.domain.dto.system.SysRoleDto;
import cn.com.geasy.marketing.domain.entity.system.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * SysRole 与 SysRoleDto 互转
 *
 * @author phil
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleMapstruct {
    SysRoleMapstruct getInstance = Mappers.getMapper(SysRoleMapstruct.class);

    SysRole toEntity(SysRoleDto dto);

    List<SysRole> toEntityList(List<SysRoleDto> dtoList);

    SysRoleDto toDto(SysRole domain);

    List<SysRoleDto> toDtoList(List<SysRole> domainList);
}
