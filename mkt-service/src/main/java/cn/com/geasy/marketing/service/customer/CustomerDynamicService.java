package cn.com.geasy.marketing.service.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import cn.com.geasy.marketing.domain.entity.customer.CustomerLifecycleEvent;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 客户动态Service
 */
public interface CustomerDynamicService extends SuperService<CustomerDynamic> {

}
