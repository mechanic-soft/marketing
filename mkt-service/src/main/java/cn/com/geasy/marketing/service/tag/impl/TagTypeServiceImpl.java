package cn.com.geasy.marketing.service.tag.impl;

import cn.com.geasy.marketing.dao.tag.TagTypeMapper;
import cn.com.geasy.marketing.domain.entity.tag.TagType;
import cn.com.geasy.marketing.service.tag.TagTypeService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 请在此写下该类的说明
 *
 * @author yml
 * @version 1.0.0
 */
@Service
public class TagTypeServiceImpl extends SuperServiceImpl<TagTypeMapper, TagType> implements TagTypeService {
}
