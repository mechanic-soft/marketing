/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午3:00
 */
package cn.com.geasy.marketing.web.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 请在此写下该类的说明
 *
 * @author phil
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SysUserController.class)
public class SysUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetUser() throws Exception{
        this.mvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data").value("user"));
    }
}
