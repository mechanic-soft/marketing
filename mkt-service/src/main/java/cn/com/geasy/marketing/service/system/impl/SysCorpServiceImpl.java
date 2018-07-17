/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysCorpMapper;
import cn.com.geasy.marketing.domain.dto.system.SysCorpDto;
import cn.com.geasy.marketing.domain.entity.system.SysCorp;
import cn.com.geasy.marketing.mapstruct.system.SysCorpMapstruct;
import cn.com.geasy.marketing.service.system.SysCorpService;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司服务
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysCorpServiceImpl extends SuperServiceImpl<SysCorpMapper, SysCorp> implements SysCorpService {

    public Page<SysCorpDto> selectDtoPage(int pageNum) {
        Page<SysCorp> page = PageUtils.getPage(pageNum);
        page = super.selectPage(page);
        List<SysCorpDto> corpDtos = SysCorpMapstruct.getInstance.toDtoList(page.getRecords());
        return PageUtils.getPage(page, corpDtos);
    }

    @Override
    public SysCorpDto selectDtoById(Long id) {
        SysCorp sysCorp = super.selectById(id);
        return SysCorpMapstruct.getInstance.toDto(sysCorp);
    }

    @Override
    public SysCorpDto save(SysCorpDto sysCorpDto) {
        SysCorp sysCorp = SysCorpMapstruct.getInstance.toEntity(sysCorpDto);
        super.insertOrUpdate(sysCorp);
        return SysCorpMapstruct.getInstance.toDto(sysCorp);
    }
}
