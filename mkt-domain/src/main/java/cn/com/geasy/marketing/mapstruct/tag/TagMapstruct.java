package cn.com.geasy.marketing.mapstruct.tag;

import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Tag与TagDto互转
 *
 * @author yml
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapstruct {
    TagMapstruct getInstance = Mappers.getMapper(TagMapstruct.class);

    Tag toEntity(TagDto dto);

    List<Tag> toEntityList(List<TagDto> dtoList);

    TagDto toDto(Tag domain);

    List<TagDto> toDtoList(List<Tag> domainList);
}
