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
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0.0
 */
@Service
public class TagDtoServiceImpl extends SuperServiceImpl<TagDtoMapper, TagDto> implements TagDtoService {

    /**
     * 根据客户id 查询TagDto 全部列表数据
     * @param tagDto
     * @return
     */
    @Override
    public List<TagDto> selectTagDtoList(TagDto tagDto) {

        return baseMapper.selectTagDtoList(tagDto);
    }

    /**
     * 分页查询TagDto 列表
     * @param pageNum
     * @param tagDto
     * @return
     */
    @Override
    public Page<TagDto> selectDtoPage(int pageNum, TagDto tagDto) {
        Page<TagDto> page = PageUtils.getPage(pageNum);
        List<TagDto> tagDtos = baseMapper.selectDtoPage(page, tagDto);
        page.setRecords(tagDtos);
        return page;
    }
}
