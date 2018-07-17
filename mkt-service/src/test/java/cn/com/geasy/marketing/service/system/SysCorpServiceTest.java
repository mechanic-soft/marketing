/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 上午12:13
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.dao.system.SysCorpMapper;
import cn.com.geasy.marketing.domain.dto.system.SysCorpDto;
import cn.com.geasy.marketing.domain.entity.system.SysCorp;
import cn.com.geasy.marketing.service.StartupTestApplication;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupTestApplication.class)
@AutoConfigureMockMvc
public class SysCorpServiceTest {

    @MockBean
    private SysCorpMapper corpMapper;

    @Autowired
    private SysCorpService corpService;

    @Before
    public void setUp() throws Exception{
    }

    @Test
    public void testSelectAllPage() throws Throwable {
        SysCorp corp1 = new SysCorp();
        corp1.setId(1L);
        corp1.setName("corp1");
        corp1.setStatus(1);
        corp1.setCreateTime(LocalDateTime.now());
        corp1.setCreateUser(1L);
        corp1.setUpdateTime(LocalDateTime.now());
        corp1.setUpdateUser(1L);
        SysCorp corp2 = new SysCorp();
        corp2.setId(2L);
        corp2.setName("corp2");
        corp2.setStatus(1);
        corp2.setCreateTime(LocalDateTime.now());
        corp2.setCreateUser(1L);
        corp2.setUpdateTime(LocalDateTime.now());
        corp2.setUpdateUser(1L);

        List<SysCorp> corps = Lists.newArrayList(corp1, corp2);

        given(this.corpMapper.selectPage(any(), any())).willReturn(corps);

        Page<SysCorpDto> actual = this.corpService.selectDtoPage(1);

        assertThat(actual).isNotNull();
        assertThat(actual.getRecords().size()).isEqualTo(2);
    }
}
