package cn.com.geasy.marketing.api.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.service.customer.CustomerService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
客户管理
 */
@Api(tags = "Customer", description = "客户管理接口")
@Slf4j
@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerSrv;

    @ApiOperation(value = "删除客户信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "客户ID", paramType = "body")
    })
    @DeleteMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getUser(@RequestBody List<Long> ids){
        return ResponseUtils.result(customerSrv.deleteBatchIds(ids));
    }

    @ApiOperation(value = "关联微信")
    /*@ApiImplicitParams(value = {@ApiImplicitParam(name = "nickname", value = "微信昵称", paramType = "body")
            @ApiImplicitParam(name = "id", value = "ID", paramType = "body")
    })*/
    @PatchMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelMap> releWx(@RequestBody CustomerDto customerDto){
        return ResponseUtils.result(customerSrv.releWechat(customerDto));
    }

    @ApiOperation(value = "微信好友列表")
    //@ApiImplicitParams(value = {@ApiImplicitParam(name = "pageNum", value = "页数", paramType = "body")})
    @GetMapping(path = "/wxcontacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> getWxContantByPage(@RequestParam(defaultValue = "1") int pageNum){
        return ResponseUtils.result(customerSrv.getWxContantByPage(pageNum));
    }

    @ApiOperation(value = "同步客户")
    @PostMapping(path = "/customers/wxContacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> synchronizeCustomer(@RequestBody List<WxContact> list){
        return ResponseUtils.result(customerSrv.synchronizeCustomer(list));
    }




}
