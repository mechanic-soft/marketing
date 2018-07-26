package cn.com.geasy.marketing.mapstruct.wechat;

import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactSecondDto;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WxContactSecondMapstruct {
    WxContactSecondMapstruct getInstance = Mappers.getMapper(WxContactSecondMapstruct.class);

    WxContact toEntity(WxContactSecondDto dto);

    List<WxContact> toEntityList(List<WxContactSecondDto> dtoList);

    WxContactSecondDto toDto(WxContact domain);

    List<WxContactSecondDto> toDtoList(List<WxContact> domainList);
}
