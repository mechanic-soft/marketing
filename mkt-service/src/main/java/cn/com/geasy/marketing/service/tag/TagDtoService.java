/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/17 下午12:11
 */
package cn.com.geasy.marketing.service.tag;

import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.entity.tag.Tag;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 *
 *
 * @version 1.0.0
 */
public interface TagDtoService extends SuperService<TagDto> {
   /**
    * 根据客户id 查询TagDto 全部列表数据
    * @param tagDto
    * @return
    */
   public List<TagDto> selectTagDtoList(TagDto tagDto);

   /**
    * 分页查询TagDto 列表
    * @param pageNum
    * @param tagDto
    * @return
    */
   public Page<TagDto> selectDtoPage(int pageNum, TagDto tagDto);
}
