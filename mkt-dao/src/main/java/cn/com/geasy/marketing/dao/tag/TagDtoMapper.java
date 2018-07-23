/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午6:03
 */
package cn.com.geasy.marketing.dao.tag;

import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version 1.0.0
 */
public interface TagDtoMapper extends SuperMapper<TagDto> {

    List<TagDto> selectTagDtoList(@Param("tagDto") TagDto tagDto);

    List<TagDto> selectDtoPage(Page<TagDto> page,@Param("tagDto")  TagDto tagDto);
}
