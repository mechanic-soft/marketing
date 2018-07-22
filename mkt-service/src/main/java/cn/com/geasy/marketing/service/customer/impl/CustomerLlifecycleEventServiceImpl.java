package cn.com.geasy.marketing.service.customer.impl;

import cn.com.geasy.marketing.dao.customer.CustomerLlifecycleEventMapper;
import cn.com.geasy.marketing.domain.entity.customer.CustomerLlifecycleEvent;
import cn.com.geasy.marketing.service.customer.CustomerLlifecycleEventService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by yml on 2018/7/22.
 */

/**
 * 客户生命周期事件 Service 实现
 */
@Service
public class CustomerLlifecycleEventServiceImpl extends SuperServiceImpl<CustomerLlifecycleEventMapper, CustomerLlifecycleEvent> implements CustomerLlifecycleEventService {
}
