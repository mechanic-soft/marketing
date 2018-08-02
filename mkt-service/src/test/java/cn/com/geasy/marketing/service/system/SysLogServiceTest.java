package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.domain.dto.system.SysLogDto;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/8/1 下午5:45
 */
public class SysLogServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    SysLogService sysLogService;

    @DatabaseSetups(value = {
            @DatabaseSetup(value = "/dbunit/sys_logs.setUp.xml")
    })
    @Test
    public void selectDtoPage() {
        Page<SysLogDto> sysLogPage = this.sysLogService.selectDtoPage(1);
        assertThat(sysLogPage.getTotal()).isEqualTo(3);
        assertThat(sysLogPage.getTotal()).isEqualTo(3);
    }

    @Test
    public void selectDtoById() {
    }
}