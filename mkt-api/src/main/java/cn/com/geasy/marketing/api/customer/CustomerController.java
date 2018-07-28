package cn.com.geasy.marketing.api.customer;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.dto.tag.TagDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactSecondDto;
import cn.com.geasy.marketing.service.customer.CustomerService;
import cn.com.geasy.marketing.service.tag.TagDtoService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
    private TagDtoService tagDtoSrv;

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

    @ApiOperation(value = "客户列表查询")
    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> selectPage(
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) Integer isAddWechat,
            @RequestParam(required = false) List<Long> tagIds,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(required = false) LocalDate callTimeStart,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(required = false) LocalDate callTimeEnd,
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize)
    {

        //localDateToStr(callTimeStart);
        CustomerDto customerDto = new CustomerDto(nickname,isAddWechat,tagIds,callTimeStart,callTimeEnd, SessionUtils.getUserId());
        return ResponseUtils.result(customerSrv.selectDtoPage(pageNum==null?1:pageNum,pageSize==null?10:pageSize,customerDto));
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
    public ResponseEntity<ModelMap> getWxContantList(){
        return ResponseUtils.result(customerSrv.getWxContantList());
    }

    @ApiOperation(value = "同步客户")
    @PostMapping(path = "/customers/wxContacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> synchronizeCustomer(@RequestBody List<WxContactDto> list){
        return ResponseUtils.result(customerSrv.synchronizeCustomer(list));
    }

    @ApiOperation(value = "新增客户标签")
    @PostMapping(path = "/customers/{customerId}/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> addCustomerTag(@PathVariable("customerId") Long customerId,@RequestBody List<Long> tagIds){
        return ResponseUtils.result(customerSrv.addCustomerTag(customerId,tagIds));
    }

    @ApiOperation(value = "客户生命周期事件列表")
    @PostMapping(path = "/customers/{customerId}/lifecycles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> customerLifecycle(@PathVariable("customerId") Long customerId){
        return ResponseUtils.result(customerSrv.customerLifecycleById(customerId));
    }



    @ApiOperation(value = "客户标签列表查询")
    @GetMapping(path = "/customers/{customerId}/tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> selectTagList(@PathVariable(required = true) Long customerId) {
        TagDto tagDto = new TagDto();
        tagDto.setCustomerId(customerId);
        return ResponseUtils.result(tagDtoSrv.selectTagDtoList(tagDto));
    }


    @ApiOperation(value = "同步微信客户列表")
    @PostMapping(path = "/customers/wxUsers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> synchronizeWxUserList(@RequestBody List<WxContactSecondDto> list){
        return ResponseUtils.result(customerSrv.synchronizeWxUserList(list));
    }


}
