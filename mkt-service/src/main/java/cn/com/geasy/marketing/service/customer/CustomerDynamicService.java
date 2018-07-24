package cn.com.geasy.marketing.service.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerArticleDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicStatisticDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerInteractionDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.time.LocalDate;
import java.util.List;

/**
 * 客户动态服务接口
 * @author tingfei.wang
 * @date 2018年07月22日 14:20:00
 */
public interface CustomerDynamicService extends SuperService<CustomerDynamic> {

    /**
     * 保存客户动态信息
     *
     * @param customerDynamicDto 客户动态信息
     * @return true or false
     */
    boolean save(CustomerDynamicDto customerDynamicDto);

    /**
     * 获取客户动态
     * @param startDate 开始时间
     * @param pageNum 页码
     * @return 返回客户动态信息列表
     */
    Page<CustomerDynamicDto> getCustomerDynamics(LocalDate startDate, int pageNum);

    /**
     * 客户行为统计
     * @param startDate 开始时间
     * @return 返回客户行为统计数据
     */
    List<CustomerDynamicStatisticDto> getCustomerDynamicStatistics(LocalDate startDate);

    /**
     * 文章阅读动态统计
     * @param startDate 开始时间
     * @param pageNum 页码
     * @return 返回文章阅读动态统计数据
     */
    Page<CustomerArticleDynamicStatisticsDto> getCustomerArticleDynamicStatistics(LocalDate startDate, int pageNum);

    /**
     * 客户互动统计
     * @param startDate
     * @param pageNum
     * @return 返回客户互动统计数据
     */
    Page<CustomerInteractionDynamicStatisticsDto> getCustomerInteractionDynamicStatistics(LocalDate startDate, int pageNum);
}
