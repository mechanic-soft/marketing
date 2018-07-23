/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/23 下午9:21
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysLogDto;
import cn.com.geasy.marketing.domain.entity.system.SysLog;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;
import org.springframework.stereotype.Service;

/**
 * 日志服务接口
 *
 * @author phil
 * @version 1.0.0
 */
@Service
public interface SysLogService extends SuperService<SysLog> {
    /**
     * 返回所有日志的分页列表
     *
     * @param pageNum 页码
     * @return Page&lt;SysLogDto&gt; 日志分页列表
     */
    Page<SysLogDto> selectDtoPage(int pageNum);

    /**
     * 返回匹配指定ID的日志
     *
     * @param id 日志ID
     * @return SysLogDto 日志信息
     */
    SysLogDto selectDtoById(Long id);
}
