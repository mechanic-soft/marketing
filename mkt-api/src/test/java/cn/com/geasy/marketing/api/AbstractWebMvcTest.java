/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/12 下午4:04
 */
package cn.com.geasy.marketing.api;

import cn.com.geasy.marketing.StartupTestApplication;
import com.gitee.mechanic.test.AbstractTests;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * API层测试父类
 *
 * @author phil
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupTestApplication.class)
@AutoConfigureMockMvc
public class AbstractWebMvcTest extends AbstractTests {
}
