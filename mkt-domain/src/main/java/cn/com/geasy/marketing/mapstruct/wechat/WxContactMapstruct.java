package cn.com.geasy.marketing.mapstruct.wechat;

import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by yml on 2018/7/20.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WxContactMapstruct {
    WxContactMapstruct getInstance = Mappers.getMapper(WxContactMapstruct.class);

    WxContact toEntity(WxContactDto dto);

    List<WxContact> toEntityList(List<WxContactDto> dtoList);

    WxContactDto toDto(WxContact domain);

    List<WxContactDto> toDtoList(List<WxContact> domainList);
}
