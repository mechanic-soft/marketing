/**
 * @author tingfei.wang
 * @date 2018年07月22日 15:11:00
 */
package cn.com.geasy.marketing.api.customer;


import cn.com.geasy.marketing.api.AbstractWebMvcTest;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.service.customer.CustomerDynamicService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test for {@link CustomerDynamicController}
 * @author tingfei.wang
 * @version 1.0.0
 */
public class CustomerDynamicControllerTest extends AbstractWebMvcTest {

    @MockBean
    private CustomerDynamicService customerDynamicService;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {

        CustomerDynamicDto expect = new CustomerDynamicDto();
        expect.setEvent(0);
        expect.setEventDate(LocalDateTime.now());
        expect.setArticleId(1L);
        expect.setArticleTitle("测试标题");
        expect.setArticleTag("测试标签");
        expect.setReadTime(69);
        expect.setIsFullRead(1);
        expect.setNickName("测试昵称");

        //given(this.customerDynamicService.save(expect)).willReturn(expect);

        this.mvc.perform(post("/v1/customerDynamics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

}