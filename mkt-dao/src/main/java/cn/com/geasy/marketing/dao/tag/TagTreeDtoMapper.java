/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午6:03
 */
package cn.com.geasy.marketing.dao.tag;

import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.dto.tag.TagTreeDto;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0.0
 */
public interface TagTreeDtoMapper extends SuperMapper<TagTreeDto> {

    List<TagTreeDto> findTagType(@Param("tagTreeDto") TagTreeDto tagTreeDto);
    List<TagTreeDto> findTag(@Param("tagTreeDto") TagTreeDto tagTreeDto);
}
