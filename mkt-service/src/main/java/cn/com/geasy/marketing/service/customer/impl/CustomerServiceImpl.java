package cn.com.geasy.marketing.service.customer.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.customer.CustomerMapper;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.mapstruct.wechat.WxContactMapstruct;
import cn.com.geasy.marketing.service.customer.CustomerService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.gitee.mechanic.mybatis.utils.PageUtils;
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
        List<WxContact> list = wxContactService.selectList(ew);
        //步骤二:更新该记录的微信联系人ID   设置关联
        if(!CollectionUtils.isEmpty(list)){
            WxContact dbWxContact =  list.get(0);
            Customer customer = new Customer();
            customer.setWxContactId(dbWxContact.getId());
            customer.setWxId(dbWxContact.getUsername());
            customer.setId(customerDto.getId());
            super.baseMapper.updateById(customer);
            //设置为已经同步，及关联
            WxContact wxContact = new WxContact();
            wxContact.setIsSync(Const.ONE);
            wxContact.setId(dbWxContact.getId());
            wxContactService.updateById(wxContact);
            dbCustomerDto = super.baseMapper.findById(customerDto);
        }
        //返回记录
        return dbCustomerDto;
    }

    @Override
    public Page<WxContactDto> getWxContantByPage(int pageNum) {
        //获取当前登录用户
        Long userId = SessionUtils.getUserId();
        EntityWrapper<WxContact> ew=new EntityWrapper<WxContact>();
        ew.where("user_id = {0}",userId);
        Page<WxContact> page = PageUtils.getPage(pageNum);
        page =  wxContactService.selectPage(page,ew);
        //将对应实体转换为对应实体的DTO
        List<WxContactDto> corpDtos = WxContactMapstruct.getInstance.toDtoList(page.getRecords());
        return PageUtils.getPage(page, corpDtos);
    }

    @Transactional
    @Override
    public String synchronizeCustomer(List<WxContact> list) {
        boolean flag = wxContactService.updateBatchById(list);
        return flag?Const.SYNCHRONIZE_SUCCESS:Const.SYNCHRONIZE_FAIL;
    }

}

