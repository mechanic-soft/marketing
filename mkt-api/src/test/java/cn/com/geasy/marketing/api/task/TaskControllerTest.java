/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午2:40
 */
package cn.com.geasy.marketing.api.task;

import cn.com.geasy.marketing.api.AbstractWebMvcTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link TaskController}
 *
 * @author phil
 * @version 1.0.0
 */
public class TaskControllerTest extends AbstractWebMvcTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetTasks() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("tasks"));
    }
}
