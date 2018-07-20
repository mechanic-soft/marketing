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
    public CustomerDto releWx(CustomerDto customerDto) {
        CustomerDto dbCustomerDto = null;
        //步骤一:根据微信昵称匹配对应的微信联系人记录
        EntityWrapper ew=new EntityWrapper();
        ew.setEntity(new WxContact());
        String nickname=customerDto.getNickname();
        ew.where("nickname = {0}",nickname);
        List<Long> list = wxContactService.selectObjs(ew);
        System.out.println("--------  size: "+list.size()+"   ---------------------");
        System.out.println(list.toString());
        //步骤二:更新该记录的微信联系人ID   设置关联
        if(null != list && list.size() >0){
            Long dbid = list.get(0);
            Customer customer = new Customer();
            customer.setWxContactId(dbid);
            customer.setId(customerDto.getId());
            Integer result = super.baseMapper.updateById(customer);
            System.out.println("影响行数:"+result);
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

//    private Session getCurrentUser(){
//        //shiro管理的session
//        Subject currentUser = SecurityUtils.getSubject();
//        Session session = currentUser.getSession();
//        session.getAttribute("user");
//        return SecurityUtils.getSubject().getSession();
//    }
}

