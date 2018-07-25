package cn.com.geasy.marketing.mapstruct.tag;

import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.dto.tag.TagTypeDto;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.domain.entity.tag.TagType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author yml
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagTypeMapstruct {
    TagTypeMapstruct getInstance = Mappers.getMapper(TagTypeMapstruct.class);

    TagType toEntity(TagTypeDto dto);

    List<TagType> toEntityList(List<TagTypeDto> dtoList);

    TagTypeDto toDto(TagType domain);

    List<TagTypeDto> toDtoList(List<TagType> domainList);
}
