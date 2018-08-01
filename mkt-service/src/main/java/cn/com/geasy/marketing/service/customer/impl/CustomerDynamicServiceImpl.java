package cn.com.geasy.marketing.service.customer.impl;

import cn.com.geasy.marketing.dao.customer.CustomerDynamicMapper;
import cn.com.geasy.marketing.dao.customer.CustomerMapper;
import cn.com.geasy.marketing.dao.wechat.WxContactMapper;
import cn.com.geasy.marketing.domain.dto.customer.CustomerArticleDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicStatisticDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerInteractionDynamicStatisticsDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.mapstruct.customer.CustomerDynamicMapstruct;
import cn.com.geasy.marketing.service.customer.CustomerDynamicService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

/**
 * 客户动态服务
 * @author tingfei.wang
 * @version 1.0.0
 */
@Service
public class CustomerDynamicServiceImpl extends SuperServiceImpl<CustomerDynamicMapper, CustomerDynamic> implements CustomerDynamicService {

    private final CustomerDynamicMapper customerDynamicMapper;

    private final WxContactMapper wxContactMapper;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerDynamicServiceImpl(
      CustomerDynamicMapper customerDynamicMapper,
      WxContactMapper wxContactMapper,
      CustomerMapper customerMapper) {
        this.customerDynamicMapper = customerDynamicMapper;
        this.wxContactMapper = wxContactMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public boolean save(CustomerDynamicDto customerDynamicDto) {
        CustomerDynamic customerDynamic = CustomerDynamicMapstruct.getInstance.toEntity(customerDynamicDto);

        //根据昵称匹配微信联系人
        EntityWrapper<WxContact> wxContactEntityWrapper = new EntityWrapper<>();
        wxContactEntityWrapper.eq("nick_name",customerDynamicDto.getNickName());
        List<WxContact> wxContacts = this.wxContactMapper.selectList(wxContactEntityWrapper);

        if(CollectionUtils.isEmpty(wxContacts)){
            return false;
        }

        for(WxContact wxContact : wxContacts){
            //根据微信联系人ID查询客户信息
            EntityWrapper<Customer> customerEntityWrapper = new EntityWrapper<>();
            customerEntityWrapper.eq("wx_contact_id", wxContact.getId()).eq("user_id",wxContact.getUserId());
            List<Customer> customers = this.customerMapper.selectList(customerEntityWrapper);

            if(CollectionUtils.isEmpty(customers)){
                continue;
            }

            customerDynamic.setCustomerId(customers.get(0).getId());
            customerDynamic.setUserId(customers.get(0).getUserId());
            super.insertOrUpdate(customerDynamic);
        }

        return true;
    }

    @Override
    public Page<CustomerDynamicDto> getCustomerDynamics(LocalDate startDate, int pageNum) {
        //获取当前登录用户
        Long userId = SessionUtils.getUserId();
        Page<CustomerDynamicDto> page = PageUtils.getPage(pageNum);
        EntityWrapper<CustomerDynamicDto> wrapper = new EntityWrapper<>();
        wrapper.eq("cd.user_id", userId).ge("cd.event_date", startDate).between("event", 0, 2).orderBy("cd.create_time",false);
        List<CustomerDynamicDto> customerDynamicDtos = this.customerDynamicMapper.getCustomerDynamics(page, wrapper);
        return page.setRecords(customerDynamicDtos);
    }

    @Override
    public List<CustomerDynamicStatisticDto> getCustomerDynamicStatistics(LocalDate startDate) {
        //获取当前登录用户
        Long userId = SessionUtils.getUserId();
        EntityWrapper<CustomerDynamicStatisticDto> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId).ge("event_date", startDate).between("event", 0, 2).groupBy("event");
        return this.customerDynamicMapper.getCustomerDynamicStatistics(wrapper);
    }

    @Override
    public Page<CustomerArticleDynamicStatisticsDto> getCustomerArticleDynamicStatistics(LocalDate startDate, int pageNum) {
        //获取当前登录用户
        Long userId = SessionUtils.getUserId();
        Page<CustomerArticleDynamicStatisticsDto> page = PageUtils.getPage(pageNum);
        EntityWrapper<CustomerArticleDynamicStatisticsDto> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId).eq("event", 0)
               .ge("event_date", startDate)
               .groupBy("article_title").orderBy("create_time",false);
        List<CustomerArticleDynamicStatisticsDto> customerArticleDynamicStatisticsDtos = customerDynamicMapper.getCustomerArticleDynamicStatistics(page, wrapper);
        return page.setRecords(customerArticleDynamicStatisticsDtos);
    }

    @Override
    public Page<CustomerInteractionDynamicStatisticsDto> getCustomerInteractionDynamicStatistics(LocalDate startDate, int pageNum) {
        //获取当前登录用户
        Long userId = SessionUtils.getUserId();
        Page<CustomerInteractionDynamicStatisticsDto> page = PageUtils.getPage(pageNum);
        EntityWrapper<CustomerInteractionDynamicStatisticsDto> wrapper = new EntityWrapper<>();
        wrapper.eq("cd.user_id", userId).ge("cd.event_date", startDate).between("event", 0, 2).groupBy("customer_id,nick_name,head_img_url");
        List<CustomerInteractionDynamicStatisticsDto> customerInteractionDynamicStatisticsDtos = customerDynamicMapper.getCustomerInteractionDynamicStatistics(page, wrapper);

        if(CollectionUtils.isEmpty(customerInteractionDynamicStatisticsDtos)){
            return page;
        }

        Iterator<CustomerInteractionDynamicStatisticsDto> iterator = customerInteractionDynamicStatisticsDtos.iterator();
        while(iterator.hasNext()){
            CustomerInteractionDynamicStatisticsDto customerInteractionDynamicStatisticsDto = iterator.next();
            EntityWrapper<CustomerDynamicStatisticDto> customerDynamicStatisticDtoEntityWrapper = new EntityWrapper<>();
            customerDynamicStatisticDtoEntityWrapper.eq("user_id", userId).eq("customer_id", customerInteractionDynamicStatisticsDto.getCustomerId()).ge("event_date", startDate).groupBy("event");
            customerInteractionDynamicStatisticsDto.setInteraction(this.customerDynamicMapper.getCustomerDynamicStatistics(customerDynamicStatisticDtoEntityWrapper));
        }
        return page.setRecords(customerInteractionDynamicStatisticsDtos);
    }

    @Override
    public List<Long> getCustomerIdByCustomerInteractionDynamic(CustomerDynamicDto customerDynamicDto) {
        return this.customerDynamicMapper.getCustomerIdByCustomerInteractionDynamic(customerDynamicDto);
    }


}