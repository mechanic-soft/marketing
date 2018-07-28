package cn.com.geasy.marketing.service.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerLifecycleEventDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactSecondDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 客户管理 Service
 */
public interface CustomerService extends SuperService<Customer> {
    public CustomerDto releWechat(CustomerDto customerDto);
    public List<WxContactDto> getWxContantList();
    public String synchronizeCustomer(List<WxContactDto> list);
    public String addCustomerTag(Long customerId,List<Long> tagIds);
    public List<CustomerLifecycleEventDto> customerLifecycleById(Long id);

    public Page<CustomerDto>  selectDtoPage(int pageNum,int pageSize, CustomerDto customerDto);

    public String  synchronizeWxUserList(List<WxContactSecondDto> list);
}
