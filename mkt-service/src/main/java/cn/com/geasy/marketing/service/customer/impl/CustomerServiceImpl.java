package cn.com.geasy.marketing.service.customer.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.customer.CustomerMapper;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.service.customer.CustomerService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl extends SuperServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private WxContactService wxContactService;

    //设置为事务的操作
    @Transactional
    @Override
    public CustomerDto releWechat(CustomerDto customerDto) {
        CustomerDto dbCustomerDto = null;
        //步骤一:根据微信昵称匹配对应的微信联系人记录
        EntityWrapper<WxContact> ew=new EntityWrapper<WxContact>();
        String nickname=customerDto.getNickname();
        ew.where("nickname = {0}",nickname);
        List list = wxContactService.selectObjs(ew);
        //步骤二:更新该记录的微信联系人ID   设置关联
        if(!CollectionUtils.isEmpty(list)){
            Long dbid = (Long) list.get(0);
            Customer customer = new Customer();
            customer.setWxContactId(dbid);
            customer.setId(customerDto.getId());
            Integer result = super.baseMapper.updateById(customer);
            //设置为已经同步，及关联
            WxContact wxContact = new WxContact();
            wxContact.setIsSync(Const.ONE);
            wxContact.setId(dbid);
            boolean rs = wxContactService.updateById(wxContact);
            dbCustomerDto = super.baseMapper.findById(customerDto);
        }
        //返回记录
        return dbCustomerDto;
    }
}

