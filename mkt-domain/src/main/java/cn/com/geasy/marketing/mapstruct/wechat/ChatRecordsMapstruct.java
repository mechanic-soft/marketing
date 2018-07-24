package cn.com.geasy.marketing.mapstruct.wechat;

import cn.com.geasy.marketing.domain.dto.wechat.ChatRecordsDto;
import cn.com.geasy.marketing.domain.entity.wechat.ChatRecords;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by yml on 2018/7/20.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatRecordsMapstruct {
    ChatRecordsMapstruct getInstance = Mappers.getMapper(ChatRecordsMapstruct.class);

    ChatRecords toEntity(ChatRecordsDto dto);

    List<ChatRecords> toEntityList(List<ChatRecordsDto> dtoList);

    ChatRecordsDto toDto(ChatRecords domain);

    List<ChatRecordsDto> toDtoList(List<ChatRecords> domainList);
}
