/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 下午9:23
 */
package cn.com.geasy.marketing.service.system.impl;

import cn.com.geasy.marketing.dao.system.SysLogMapper;
import cn.com.geasy.marketing.domain.dto.system.SysLogDto;
import cn.com.geasy.marketing.domain.entity.system.SysLog;
import cn.com.geasy.marketing.mapstruct.system.SysLogMapstruct;
import cn.com.geasy.marketing.service.system.SysLogService;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志服务实现
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public class SysLogServiceImpl extends SuperServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    public Page<SysLogDto> selectDtoPage(int pageNum) {
        Page<SysLog> page = PageUtils.getPage(pageNum);
        page = super.selectPage(page);
        List<SysLogDto> logDtos = SysLogMapstruct.getInstance.toDtoList(page.getRecords());
        return PageUtils.getPage(page, logDtos);
    }

    @Override
    public SysLogDto selectDtoById(Long id) {
        SysLog sysLog = super.selectById(id);
        return SysLogMapstruct.getInstance.toDto(sysLog);
    }
}
