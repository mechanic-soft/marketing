package cn.com.geasy.marketing.dao.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import com.gitee.mechanic.mybatis.base.SuperMapper;

/**
 * 客户管理Mapper
 */
public interface CustomerMapper extends SuperMapper<Customer> {
   public CustomerDto findById(CustomerDto customerDto);
}

