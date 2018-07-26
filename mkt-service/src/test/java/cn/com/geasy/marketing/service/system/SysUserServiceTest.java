/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/13 上午12:13
 */
package cn.com.geasy.marketing.service.system;

import cn.com.geasy.marketing.dao.system.SysUserMapper;
import cn.com.geasy.marketing.domain.dto.system.SysUserDto;
import cn.com.geasy.marketing.service.StartupTestApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
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
public class SysUserServiceTest {
    @MockBean
    private SysUserMapper userMapper;
    @Autowired
    private SysUserService userService;

    @Before
    public void setUp() throws Exception{
    }

    @Test
    public void testFindByUsername() throws Exception{
        SysUserDto expect = new SysUserDto();
        expect.setId(1L);
        expect.setUsername("admin");
        expect.setPassword("123456");
        expect.setRealName("name");
        expect.setWxUsername("wxusername");
        expect.setWxNickname("wxnickname");
        expect.setWxHeadImgUrl("WxHeadImgUrl");
        expect.setWxSex(1);
        expect.setWxSignature("signature");

        given(this.userMapper.findByUsername(anyString())).willReturn(expect);

        SysUserDto actual = this.userService.findByUsername("admin");

        assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void testRemove(){

    }
}
