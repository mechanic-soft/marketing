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

    /**
     * 客户列表分页查询版本1
     * @param page
     * @param customerDto
     * @return
     */
    List<CustomerDto> selectDtoPage(Page page , @Param("customerDto") CustomerDto customerDto);

    /**
     * 客户列表查询（不分页）
     * @param customerDto
     * @return
     */
    List<CustomerDto> selectDtoList(@Param("customerDto") CustomerDto customerDto);


    /**
     * 查找已经“已开户”标签下的客户
     * @param customerDtoIds
     * @return
     */
    List<CustomerDto> findOpenAccountCustomerDtoIds(@Param("ids") List<Long> customerDtoIds);

    /**
     * 规则那边的今日提醒功能用到：根据tagList标签集合和用户userid 查客户
     * @param customerDto
     * @return List<CustomerDto>
     */
    List<CustomerDto> selectCustomerDtoListByTagIdListAndUserId(@Param("customerDto") CustomerDto customerDto);
}

