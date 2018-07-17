/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/10 下午8:55
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysCorpDto;
import cn.com.geasy.marketing.domain.entity.system.SysCorp;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

/**
 * 公司服务接口
 *
 * @author phil
 * @version 1.0.0
 */
public interface SysCorpService extends SuperService<SysCorp> {
    /**
     * 返回所有公司的分页列表
     *
     * @param pageNum 页码
     * @return Page&lt;SysCorpDto&gt; 公司分页列表
     */
    Page<SysCorpDto> selectDtoPage(int pageNum);

    /**
     * 返回匹配指定ID的公司
     *
     * @param id 公司ID
     * @return SysCorpDto 公司信息
     */
    SysCorpDto selectDtoById(Long id);

    /**
     * 保存或更新公司信息
     *
     * @param sysCorpDto 公司信息
     * @return 保存或更新后的公司信息
     */
    SysCorpDto save(SysCorpDto sysCorpDto);
}
