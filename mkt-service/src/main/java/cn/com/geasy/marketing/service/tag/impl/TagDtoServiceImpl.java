/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.tag.impl;

import cn.com.geasy.marketing.dao.tag.TagDtoMapper;
import cn.com.geasy.marketing.dao.tag.TagMapper;
import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.service.tag.TagDtoService;
import cn.com.geasy.marketing.service.tag.TagService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0.0
 */
@Service
public class TagDtoServiceImpl extends SuperServiceImpl<TagDtoMapper, TagDto> implements TagDtoService {

    @Override
    public List<TagDto> selectTagDtoList(Long customerId) {
        TagDto tagDto = new TagDto();
        tagDto.setCustomerId(customerId);
        return baseMapper.selectTagDtoList(tagDto);
    }
}
