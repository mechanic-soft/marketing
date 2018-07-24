/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.tag.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.tag.TagMapper;
import cn.com.geasy.marketing.dao.tag.TagTreeDtoMapper;
import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.dto.tag.TagTreeDto;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.domain.entity.tag.TagType;
import cn.com.geasy.marketing.service.tag.TagDtoService;
import cn.com.geasy.marketing.service.tag.TagService;
import cn.com.geasy.marketing.service.tag.TagTreeDtoService;
import cn.com.geasy.marketing.service.tag.TagTypeService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @version 1.0.0
 */
@Service
@Slf4j
public class TagTreeDtoServiceImpl extends SuperServiceImpl<TagTreeDtoMapper, TagTreeDto> implements TagTreeDtoService {

    //偷懒  TODO 减少查库次数
    @Override
    public List findTagTree() {

        List<TagTreeDto> rootTagTypes =  this.baseMapper.findTagType(new TagTreeDto(0L));
        for(TagTreeDto tagTreeDto:rootTagTypes){

            List<TagTreeDto> subTagTypes = this.baseMapper.findTagType(new TagTreeDto(tagTreeDto.getId()));
            for(TagTreeDto subTagType:subTagTypes){
                List<TagTreeDto> tagTypes = this.baseMapper.findTag(new TagTreeDto(subTagType.getId()));
                subTagType.setSubTag(tagTypes);
            }
            tagTreeDto.setSubTag(subTagTypes);
        }
        log.info(rootTagTypes.toString());
        return rootTagTypes;
    }
}
