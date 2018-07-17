/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/11 下午6:16
 */
package cn.com.geasy.marketing.api.system;

import cn.com.geasy.marketing.api.AbstractWebMvcTest;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import cn.com.geasy.marketing.service.system.SysUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test for {@link SysUserController}
 *
 * @author phil
 * @version 1.0.0
 */
public class SysUserContorllerTest extends AbstractWebMvcTest {
    @MockBean
    private SysUserService sysUserService;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUser() throws Exception {

        SysUser expect = new SysUser();
        expect.setId(1L);
        expect.setUsername("admin");
        expect.setPassword("123456");
        expect.setSalt("salt");
        expect.setRealName("name");
        expect.setWxUsername("wxusername");
        expect.setWxNickname("wxnickname");
        expect.setWxHeadImgUrl("WxHeadImgUrl");
        expect.setWxSex(1);
        expect.setWxSignature("signature");

        given(this.sysUserService.selectById(anyLong())).willReturn(expect);

        this.mvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.username").value("admin"))
                .andExpect(jsonPath("$.data.password").value("123456"))
                .andExpect(jsonPath("$.data.realName").value("name"))
                .andExpect(jsonPath("$.data.wxUsername").value("wxusername"))
                .andExpect(jsonPath("$.data.wxNickname").value("wxnickname"))
                .andExpect(jsonPath("$.data.wxHeadImgUrl").value("WxHeadImgUrl"))
                .andExpect(jsonPath("$.data.wxSex").value(1))
                .andExpect(jsonPath("$.data.wxSignature").value("signature"));
    }
}
