package cn.com.geasy.marketing.service.customer.impl;

import cn.com.geasy.marketing.dao.customer.CustomerMapper;
import cn.com.geasy.marketing.dao.system.SysCorpMapper;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.service.customer.CustomerService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends SuperServiceImpl<CustomerMapper, Customer> implements CustomerService {
}
