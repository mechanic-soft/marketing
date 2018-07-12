/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午11:13
 */
package cn.com.geasy.marketing.mapstruct;

import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * SysUser 与 SysUserDto 互转
 *
 * @author phil
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysUserMapstruct {
    SysUserMapstruct getInstance = Mappers.getMapper(SysUserMapstruct.class);

    SysUser toDomain(SysUserDto dto);

    List<SysUser> toDomainList(List<SysUserDto> dtoList);

    SysUserDto toDto(SysUser domain);

    List<SysUserDto> toDtoList(List<SysUser> domainList);
}
