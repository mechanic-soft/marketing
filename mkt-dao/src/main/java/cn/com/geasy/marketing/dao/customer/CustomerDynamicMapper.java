package cn.com.geasy.marketing.dao.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerArticleDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicStatisticDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerInteractionDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerLifecycleEventDto;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户动态数据访问接口
 */
@Repository
public interface CustomerDynamicMapper extends SuperMapper<CustomerDynamic> {

    /**
     * 获取客户动态
     * @param page 分页查询类
     * @param wrapper 查询条件封装类
     * @return List<CustomerDynamicDto>客户动态数据列表
     */
    List<CustomerDynamicDto> getCustomerDynamics(Page<CustomerDynamicDto> page, @Param("ew") EntityWrapper<CustomerDynamicDto> wrapper);

    /**
     * 客户行为统计
     * @param wrapper 查询条件封装类
     * @return List<CustomerDynamicStatisticDto>客户行为统计数据列表
     */
    List<CustomerDynamicStatisticDto> getCustomerDynamicStatistics(@Param("ew")EntityWrapper<CustomerDynamicStatisticDto> wrapper);

    /**
     * 文章阅读动态统计
     * @param page 分页查询类
     * @param wrapper 查询条件封装类
     * @return List<CustomerArticleDynamicStatisticsDto>文章阅读动态统计数据列表
     */
    List<CustomerArticleDynamicStatisticsDto>getCustomerArticleDynamicStatistics(Page<CustomerArticleDynamicStatisticsDto> page,@Param("ew") EntityWrapper<CustomerArticleDynamicStatisticsDto> wrapper);

    /**
     * 客户互动统计
     * @param page 分页查询类
     * @param wrapper 查询条件封装类
     * @return List<CustomerInteractionDynamicStatisticsDto>客户互动统计数据列表
     */
    List<CustomerInteractionDynamicStatisticsDto> getCustomerInteractionDynamicStatistics(Page<CustomerInteractionDynamicStatisticsDto> page, @Param("ew") EntityWrapper<CustomerInteractionDynamicStatisticsDto> wrapper);

    /***
     * 获取客户动态表的客户id
     * @param customerDynamicDto
     * @return List<Long> 返回存放客户id的list集合
     */
    List<Long> getCustomerIdByCustomerInteractionDynamic(CustomerDynamicDto customerDynamicDto);

    /**
     * 获取事件(0=阅读,1=订阅,2=联系,3=转发）的最早一条记录
     * @param customerId 客户ID
     * @return CustomerDynamicDto
     */
    List<CustomerLifecycleEventDto> getFirstOneInEvent(Long customerId);
}

