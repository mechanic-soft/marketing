package cn.com.geasy.marketing.dao.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户管理Mapper
 */
public interface CustomerMapper extends SuperMapper<Customer> {
    public CustomerDto findById(CustomerDto customerDto);
    List<CustomerDto> selectDtoPage(Page page , @Param("customerDto") CustomerDto customerDto);

}

