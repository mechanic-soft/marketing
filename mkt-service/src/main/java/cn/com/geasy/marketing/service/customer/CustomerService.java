package cn.com.geasy.marketing.service.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.system.SysCorp;
import com.gitee.mechanic.mybatis.base.SuperService;

/**
 * 客户管理 Service
 */
public interface CustomerService extends SuperService<Customer> {
    public CustomerDto releWechat(CustomerDto customerDto);
}
