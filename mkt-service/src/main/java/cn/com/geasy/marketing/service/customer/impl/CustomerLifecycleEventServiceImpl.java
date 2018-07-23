package cn.com.geasy.marketing.service.customer.impl;
import cn.com.geasy.marketing.dao.customer.CustomerLifecycleEventMapper;
import cn.com.geasy.marketing.domain.entity.customer.CustomerLifecycleEvent;
import cn.com.geasy.marketing.service.customer.CustomerLifecycleEventService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 客户生命周期事件实体ServiceImpl
 */
/**
 * 客户生命周期事件 Service 实现
 *
 * @author yml
 * @version 1.0.0
 */
@Slf4j
@Service
public class CustomerLifecycleEventServiceImpl extends SuperServiceImpl<CustomerLifecycleEventMapper, CustomerLifecycleEvent> implements CustomerLifecycleEventService {



}
