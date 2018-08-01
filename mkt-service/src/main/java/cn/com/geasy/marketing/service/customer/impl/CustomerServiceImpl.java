package cn.com.geasy.marketing.service.customer.impl;

import cn.com.geasy.marketing.contant.Const;
import cn.com.geasy.marketing.dao.customer.CustomerDynamicMapper;
import cn.com.geasy.marketing.dao.customer.CustomerMapper;
import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.dto.customer.CustomerLifecycleEventDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactDto;
import cn.com.geasy.marketing.domain.dto.wechat.WxContactSecondDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.customer.CustomerLifecycleEvent;
import cn.com.geasy.marketing.domain.entity.customer.ReleCustomerTag;
import cn.com.geasy.marketing.domain.entity.wechat.WxContact;
import cn.com.geasy.marketing.mapstruct.customer.CustomerLifecycleEventMapstruct;
import cn.com.geasy.marketing.mapstruct.wechat.WxContactMapstruct;
import cn.com.geasy.marketing.service.customer.CustomerLifecycleEventService;
import cn.com.geasy.marketing.service.customer.CustomerService;
import cn.com.geasy.marketing.service.customer.ReleCustomerTagService;
import cn.com.geasy.marketing.service.wechat.WxContactService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CustomerServiceImpl extends SuperServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private WxContactService wxContactService;
    @Autowired
    private CustomerLifecycleEventService cleSrv;
    @Autowired
    private ReleCustomerTagService rctSrv;

    @Autowired
    private ReleCustomerTagService releCustomerTagService;

    @Autowired
    private CustomerLifecycleEventService customerLifecycleEventService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerDynamicMapper customerDynamicMapper;



    //设置为事务的操作
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public CustomerDto releWechat(CustomerDto customerDto) {
        CustomerDto dbCustomerDto = null;
        //步骤一:根据微信昵称匹配对应的微信联系人记录
        EntityWrapper<WxContact> ew=new EntityWrapper<WxContact>();
        String nickname=customerDto.getNickName();
        ew.where("user_id = {0}",SessionUtils.getUserId());
        if(StringUtils.isNotBlank(nickname)){
            ew.andNew("BINARY nick_name = {0}",nickname);
        }
        List<WxContact> list = wxContactService.selectList(ew);
        //步骤二:更新该记录的微信联系人ID   设置关联
        if(!CollectionUtils.isEmpty(list)){
            WxContact dbWxContact =  list.get(0);
            Customer customer = new Customer();
            customer.setWxContactId(dbWxContact.getId());
            customer.setWxId(dbWxContact.getUserName());
            customer.setId(customerDto.getId());
            //设置修改为当前用户
            customer.setUpdateUser(SessionUtils.getUserId());
            super.baseMapper.updateById(customer);
            //设置为已经同步，及关联
            WxContact wxContact = new WxContact();
            wxContact.setIsSync(Const.ONE);
            wxContact.setId(dbWxContact.getId());
            //设置修改为当前用户
            wxContact.setUpdateUser(SessionUtils.getUserId());
            wxContactService.updateById(wxContact);
            dbCustomerDto = super.baseMapper.findById(customerDto);
        }
        //返回记录
        return dbCustomerDto;
    }

    @Override
    public List<WxContactDto> getWxContantList() {
        //获取当前登录用户
        Long userId = SessionUtils.getUserId();
        EntityWrapper<WxContact> ew=new EntityWrapper<WxContact>();
        ew.where("user_id = {0}",userId);

        //将对应实体转换为对应实体的DTO
        List<WxContactDto> corpDtos = WxContactMapstruct.getInstance.toDtoList(wxContactService.selectList(ew));
        return corpDtos;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public String synchronizeCustomer(List<WxContactDto> list) {
        List<WxContact> wxContacts = WxContactMapstruct.getInstance.toEntityList(list);
        List<Customer> customers = new ArrayList<>();
        //设置修改为当前用户
        for (WxContact item:wxContacts) {
            item.setUpdateUser(SessionUtils.getUserId());
            customers.add(new Customer(item.getId(),SessionUtils.getUserId(),LocalDateTime.now(),SessionUtils.getUserId()));
        }
        this.insertBatch(customers);
        boolean flag = wxContactService.updateBatchById(wxContacts);
        return flag?Const.SYNCHRONIZE_SUCCESS:Const.SYNCHRONIZE_FAIL;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public String addCustomerTag(Long customerId, List<Long> tagIds) {
        List<ReleCustomerTag> list = new ArrayList<ReleCustomerTag>();
        boolean result = false;
        for (Long item:tagIds) {
            ReleCustomerTag releCustomerTag = new ReleCustomerTag();
            releCustomerTag.setCustomerId(customerId);
            releCustomerTag.setTagId(item);
            //设置为当前用户ID
            releCustomerTag.setCreateUser(SessionUtils.getUserId());
            //加入List集合中
            list.add(releCustomerTag);
        }

        if(!CollectionUtils.isEmpty(list)){
            //根据客户ID删除客户标签
            EntityWrapper<ReleCustomerTag> ew=new EntityWrapper<ReleCustomerTag>();
            ew.where("customer_id = {0}",customerId);
            releCustomerTagService.delete(ew);
            //保存数据库表
            result = releCustomerTagService.insertBatch(list);
        }
        return result?Const.SAVE_SUCCESS:Const.SAVE_FAIL;
    }

    @Override
    public List<CustomerLifecycleEventDto> customerLifecycleById(Long id) {
        boolean flag = false;
        //步骤一：自定义查询接口
        EntityWrapper<CustomerLifecycleEvent> ew=new EntityWrapper<CustomerLifecycleEvent>();
        //获取当前登录用户
        Long userId = SessionUtils.getUserId();
        //默认升序
        ew.where("user_id = {0}",userId);
        if(null != id){
            flag = true;
            ew.andNew("customer_id={0}",id);
        }
        ew.orderBy("event_date",true);
        List<CustomerLifecycleEvent> list = customerLifecycleEventService.selectList(ew);
        //
        List<CustomerLifecycleEventDto> result = CustomerLifecycleEventMapstruct.getInstance.toDtoList(list);
        return CollectionUtils.isEmpty(result) || !flag ?null:result;
    }

    @Override
    public Page<CustomerDto> selectDtoPage(int pageNum, int pageSize,CustomerDto customerDto) {
        Page<CustomerDto> page = new Page<>(pageNum,pageSize);
        List<CustomerDto> customerDtos = baseMapper.selectDtoPage(page, customerDto);//selectMyDtoPage
        initCustomerDto(customerDtos);
        page.setRecords(customerDtos);
        return page;
    }

    @Override
    public List<CustomerDto> selectDtoList(CustomerDto customerDto) {

        List<CustomerDto> customerDtos = baseMapper.selectDtoList(customerDto);//selectMyDtoPage
        initCustomerDto(customerDtos);

        return customerDtos;
    }


    /**
     * 设置是"否加微信"、"是否开户"、用户标签等信息
     * TODO “最近联系”字段
     * @param customerDtos
     */
    private void initCustomerDto(List<CustomerDto> customerDtos) {
        List<Long> customerDtoIds =  new ArrayList<>();
        for(CustomerDto customerDto:customerDtos) {
            //wx_contact_id   null 就是未加微信

            if (StringUtils.isBlank(customerDto.getWxContactId())) {
                customerDto.setIsAddWechat(0);
            } else {
                customerDto.setIsAddWechat(1);
            }

            customerDtoIds.add(customerDto.getId());
        }

        /**加入标签id数组, 加入是否开户字段 */
        Map<Long,List<Long>> tagReles = getReles(customerDtoIds);
        Map<Long,Integer> openAccountMap = getOpenAccountMap(customerDtoIds);
        for(CustomerDto customerDto:customerDtos){
            List<Long> tags = tagReles.get(customerDto.getId());
            customerDto.setTagIds(CollectionUtils.isEmpty(tags)?new ArrayList<Long>():tags);
            customerDto.setIsOpenAccount(openAccountMap.containsKey(customerDto.getId())?1:0);
        }


    }


    /**
     * 根据客户id  查询当前查询页所需 “是否 开户” 数据集合
     * (已开户获取方式，从标签表去拿   "已开户"  标签)
     * @param customerDtoIds
     * @return  Map<Long 客户id,Integer 开户值为3>
     */
    private Map<Long,Integer> getOpenAccountMap(List<Long> customerDtoIds) {
        Map<Long,Integer> openAccountMap = new HashMap<>();
        List<CustomerDto> customersDtos = this.baseMapper.findOpenAccountCustomerDtoIds(customerDtoIds);

        if(CollectionUtils.isNotEmpty(customersDtos)){
            for(CustomerDto customersDto:customersDtos){
                openAccountMap.put(customersDto.getId(),1);
            }
        }
        return openAccountMap;
    }


    /***
     * 组装当前查询页所需的标签数据集合
     * @param customerDtoIds
     * @return   Map<Long 客户id , List<Long> 对应标签集合>
     */
    private Map<Long,List<Long>> getReles(List<Long> customerDtoIds) {

        List<ReleCustomerTag> releCustomerTags = rctSrv.selectList(new EntityWrapper<ReleCustomerTag>().in("customer_id",customerDtoIds.toArray()));
        Map<Long,List<Long>> reles = new HashMap<>();
        for(ReleCustomerTag releCustomerTag:releCustomerTags) {

            Long customerId = releCustomerTag.getCustomerId();
            List<Long> tagsArrayBycustomerId =  reles.get(customerId);
            if(CollectionUtils.isEmpty(tagsArrayBycustomerId)){
                tagsArrayBycustomerId = new ArrayList<>();

            }
            tagsArrayBycustomerId.add(releCustomerTag.getTagId());
            reles.put(customerId,tagsArrayBycustomerId);

        }
        return reles;
    }

    /**
     * 同步微信联系人信息
     *
     *
     * @param list
     * @return
     */
    @Override
    public String synchronizeWxUserList(List<WxContactSecondDto> list) {
        boolean flag = wxContactService.inserOrUpdateBatchByUin(list);

        return flag?Const.SYNCHRONIZE_SUCCESS:Const.SYNCHRONIZE_FAIL;
    }

    @Override
    public List<CustomerLifecycleEventDto> newCustomerLifecycle(Long customerId) {
        List<CustomerLifecycleEventDto> customerLifecycleEventDtos = new ArrayList<>();
        Customer customer = this.customerMapper.selectById(customerId);
        if(customer == null){
            return customerLifecycleEventDtos;
        }

        if( !StringUtils.isBlank(customer.getCallTime())){
            String callTimeStr = customer.getCallTime();
            callTimeStr = callTimeStr.substring(0, 19);
            //添加呼叫生命周期事件
            customerLifecycleEventDtos.add(new CustomerLifecycleEventDto(6, LocalDateTime.parse(callTimeStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        };

        if( customer.getUpdateTime() != null){
            //添加加微生命周期事件
            customerLifecycleEventDtos.add(new CustomerLifecycleEventDto(4, customer.getUpdateTime()));
        };

        customerLifecycleEventDtos.addAll(this.customerDynamicMapper.getFirstOneInEvent(customerId));

        return customerLifecycleEventDtos;

    }
}

