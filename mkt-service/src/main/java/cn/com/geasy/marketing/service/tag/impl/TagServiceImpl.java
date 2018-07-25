/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:13
 */
package cn.com.geasy.marketing.service.tag.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.tag.TagMapper;
import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.dto.tag.TagTypeDto;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import cn.com.geasy.marketing.domain.entity.tag.TagType;
import cn.com.geasy.marketing.mapstruct.tag.TagMapstruct;
import cn.com.geasy.marketing.mapstruct.tag.TagTypeMapstruct;
import cn.com.geasy.marketing.service.tag.TagDtoService;
import cn.com.geasy.marketing.service.tag.TagService;
import cn.com.geasy.marketing.service.tag.TagTreeDtoService;
import cn.com.geasy.marketing.service.tag.TagTypeService;
import cn.com.geasy.marketing.utils.SessionUtils;
import cn.com.geasy.marketing.utils.SynchronizeUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class TagServiceImpl extends SuperServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagTypeService tagTypeService;

    @Autowired
    private TagDtoService tagDtoService;

    @Autowired
    private TagTreeDtoService TagTreeDtoSrv;
    @Override
    public List<Tag> findTagByArticleId(Long articleId) {
        return baseMapper.findTagByArticleId(articleId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public String addTag(TagDto tagDto) {
        Integer rs = 0;
        if (SynchronizeUtils.sendTag(tagDto.getName())) { //新增标签时同时发送到服务号系统
            Tag tag = TagMapstruct.getInstance.toEntity(tagDto);
            tag.setTagTypeId(tagDto.getSubTypeId());
            tag.setIsSys(tagDto.getIsSys());
            if (StringUtils.isNotBlank(tag.getName()) && null != tag.getTagTypeId()) {
                tag.setCreateUser(SessionUtils.getUserId());
                Integer rows = super.baseMapper.insert(tag);
                if (rows > 0) {
                    //表示已经插入成功
                    tag.setPath(tagDto.getTypeId() + Const.SPRIT + tag.getTagTypeId() + Const.SPRIT + tag.getId());
                    rs = super.baseMapper.updateById(tag);
                }
            }
        }
        return rs > 0 ? Const.SAVE_SUCCESS:Const.SAVE_FAIL;
    }

    @Override
    public String addTagType(TagTypeDto tagTypeDto) {
        boolean result = false;
        TagType tagType = TagTypeMapstruct.getInstance.toEntity(tagTypeDto);
        if(StringUtils.isNotBlank(tagType.getName()) && null!=tagType.getParentId()) {
            //获取当前登录用户
            tagType.setCreateUser(SessionUtils.getUserId());
            result = tagTypeService.insert(tagType);
        }
        return result ? Const.SAVE_SUCCESS:Const.SAVE_FAIL;
    }

    @Override
    public String removeTag(List<Long> ids) {
        Integer rows = 0;
        if(!CollectionUtils.isEmpty(ids)){
           rows = super.baseMapper.deleteBatchIds(ids);
        }
        return rows > 0 ?Const.DELETE_SUCCESS:Const.DELETE_FAIL;
    }

    @Override
    public String updateTag(TagDto tagDto) {
        //设置path
        Tag tag = TagMapstruct.getInstance.toEntity(tagDto);
        tag.setTagTypeId(tagDto.getSubTypeId());
        tag.setIsSys(tagDto.getIsSys());
        tag.setPath(tagDto.getTypeId()+Const.SPRIT+tag.getTagTypeId()+Const.SPRIT+tag.getId());
        Integer rows = super.baseMapper.updateById(tag);
        return rows > 0?Const.UPDATE_SUCCESS:Const.UPDATE_FAIL;
    }

    @Override
    public Page<TagDto> findSystemTagList(Integer pageNum) {
        TagDto tagDto = new TagDto();
        tagDto.setIsSys(0);
        return tagDtoService.selectDtoPage(pageNum,tagDto);
    }

    @Override
    public List findTagType() {

        return TagTreeDtoSrv.findTagTree();
    }
}
