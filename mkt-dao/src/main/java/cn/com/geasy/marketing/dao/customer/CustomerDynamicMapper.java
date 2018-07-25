package cn.com.geasy.marketing.dao.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerArticleDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicStatisticDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerInteractionDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户动态数据访问接口
 */
public interface CustomerDynamicMapper extends SuperMapper<CustomerDynamic> {

    List<CustomerDynamicDto> getCustomerDynamics(Page<CustomerDynamicDto> page, @Param("ew") EntityWrapper<CustomerDynamicDto> wrapper);

    List<CustomerDynamicStatisticDto> getCustomerDynamicStatistics(@Param("ew")EntityWrapper<CustomerDynamicStatisticDto> wrapper);

    List<CustomerArticleDynamicStatisticsDto>getCustomerArticleDynamicStatistics(Page<CustomerArticleDynamicStatisticsDto> page,@Param("ew") EntityWrapper<CustomerArticleDynamicStatisticsDto> wrapper);

    List<CustomerInteractionDynamicStatisticsDto> getCustomerInteractionDynamicStatistics(Page<CustomerInteractionDynamicStatisticsDto> page, @Param("ew") EntityWrapper<CustomerInteractionDynamicStatisticsDto> wrapper);

    /***
     * 获取客户动态表的客户id
     * @param customerDynamicDto
     * @return List<Long> 返回存放客户id的list集合
     */
    List<Long> getCustomerIdByCustomerInteractionDynamic(CustomerDynamicDto customerDynamicDto);
}

