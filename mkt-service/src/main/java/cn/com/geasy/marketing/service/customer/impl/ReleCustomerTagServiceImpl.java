package cn.com.geasy.marketing.service.customer.impl;

import cn.com.geasy.marketing.dao.customer.ReleCustomerTagMapper;
import cn.com.geasy.marketing.domain.entity.customer.ReleCustomerTag;
import cn.com.geasy.marketing.service.customer.ReleCustomerTagService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 关联客户标签Service 实现
 *
 * @author yml
 * @version 1.0.0
 */
@Service
public class ReleCustomerTagServiceImpl extends SuperServiceImpl<ReleCustomerTagMapper, ReleCustomerTag> implements ReleCustomerTagService {
}
