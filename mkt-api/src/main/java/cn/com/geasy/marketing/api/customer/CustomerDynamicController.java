/**
 * @author tingfei.wang
 * @date 2018年07月22日 14:10:00
 */
package cn.com.geasy.marketing.api.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.service.customer.CustomerDynamicService;
import com.gitee.mechanic.json.mapper.JsonMapper;
import com.gitee.mechanic.web.utils.RequestUtils;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 客户动态API
 * @author tingfei.wang
 * @version 1.0.0
 */
@Api(tags = "CustomerDynamic", description = "客户动态接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class CustomerDynamicController {

    private final CustomerDynamicService customerDynamicService;

    public CustomerDynamicController(CustomerDynamicService customerDynamicService) {this.customerDynamicService = customerDynamicService;}

    @ApiOperation(value = "添加客户动态")
    @PostMapping(path = "/customerDynamics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> save(@RequestBody CustomerDynamicDto customerDynamicDto) {

        String url = RequestUtils.getRequest().getRequestURL().toString();

        log.info("收到来自：" + url + "的消息……");
        log.info("消息内容：\n" + JsonMapper.instance.toJson(customerDynamicDto));

        return ResponseUtils.result(this.customerDynamicService.save(customerDynamicDto) ? "保存成功" : "添加失败：昵称不匹配。" );
    }

    @ApiOperation(value = "客户动态列表")
    @GetMapping(path = "/customerDynamics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCustomerDynamics(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate startDate,
                                                        @RequestParam(required = false, defaultValue = "1") int pageNum) {
        return ResponseUtils.result(this.customerDynamicService.getCustomerDynamics(startDate, pageNum));
    }

    @ApiOperation(value = "客户行为统计")
    @GetMapping(path = "/customerDynamics/statistics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCustomerDynamicStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate startDate) {
        return ResponseUtils.result(this.customerDynamicService.getCustomerDynamicStatistics(startDate));
    }

    @ApiOperation(value = "客户阅读文章动态统计")
    @GetMapping(path = "/customerDynamics/statistics/acticles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCustomerArticleDynamicStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate startDate,
                                                                        @RequestParam(required = false, defaultValue = "1") int pageNum) {
        return ResponseUtils.result(this.customerDynamicService.getCustomerArticleDynamicStatistics(startDate, pageNum));
    }

    @ApiOperation(value = "客户互动统计")
    @GetMapping(path = "/customerDynamics/statistics/interaction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getCustomerInteractionDynamicStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate startDate,
                                                                            @RequestParam(required = false, defaultValue = "1") int pageNum) {
        return ResponseUtils.result(this.customerDynamicService.getCustomerInteractionDynamicStatistics(startDate, pageNum));
    }


}