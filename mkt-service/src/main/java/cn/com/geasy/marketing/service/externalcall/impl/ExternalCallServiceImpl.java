package cn.com.geasy.marketing.service.externalcall.impl;

import cn.com.geasy.marketing.dao.externalcall.ExternalCallMapper;
import cn.com.geasy.marketing.domain.dto.externalCall.QuestionnaireDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.externalCall.ExternalCall;
import cn.com.geasy.marketing.domain.entity.externalCall.Questionnaire;
import cn.com.geasy.marketing.domain.entity.externalCall.ReleExternalCallQuestionnaire;
import cn.com.geasy.marketing.domain.entity.system.SysUser;
import cn.com.geasy.marketing.mapstruct.externalCall.QuestionnaireMapstruct;
import cn.com.geasy.marketing.service.customer.CustomerService;
import cn.com.geasy.marketing.service.externalcall.ExternalCallService;
import cn.com.geasy.marketing.service.externalcall.QuestionnaireService;
import cn.com.geasy.marketing.service.externalcall.ReleExternalCallQuestionnaireService;
import cn.com.geasy.marketing.service.system.SysUserService;
import cn.com.geasy.marketing.utils.SessionUtils;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
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


@Slf4j
@Service
public class ExternalCallServiceImpl extends SuperServiceImpl<ExternalCallMapper, ExternalCall> implements ExternalCallService {

    @Autowired
    private QuestionnaireService questionSrv;

    @Autowired
    private ReleExternalCallQuestionnaireService recqSrv;

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CustomerService customerService;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public String insert(ExternalCall ec, List<QuestionnaireDto> record) {

        ec.setCreateTime(LocalDateTime.now());
        //admin
        ec.setCreateUser(1L);
        ec.setUpdateUser(1L);
        this.insert(ec);

        Long externalCallId = ec.getId();
        String customerId = ec.getCustomerId();
        String remark = ec.getRemark();
        String userCode = ec.getUserCode();
        LocalDateTime callTimeStart = ec.getCallTimeStart();
        List<Questionnaire> qestionnaires = QuestionnaireMapstruct.getInstance.toEntityList(record);
        List<ReleExternalCallQuestionnaire> reles = new ArrayList<>();
        qestionnaires.forEach(qestionnaire ->{
            qestionnaire.setCreateTime(LocalDateTime.now());
            qestionnaire.setUpdateTime(LocalDateTime.now());
            qestionnaire.setCreateUser(1L);
            qestionnaire.setUpdateUser(1L);
        });
        questionSrv.insertBatch(qestionnaires);

        qestionnaires.forEach(qestionnaire->{
            reles.add(new ReleExternalCallQuestionnaire(externalCallId,qestionnaire.getId(),LocalDateTime.now(),LocalDateTime.now(),1L,1L));
        });
        recqSrv.insertBatch(reles);

        //外呼系统与系统用户表有一定对应关系，根据ExternalCall.customerId 去sys_user.callcenter_user_id 找用户id
        HashMap<String,Object> columnMap = Maps.newHashMap();
        columnMap.put("callcenter_user_id",customerId);
        List<SysUser> sysUsers = sysUserService.selectByMap(columnMap);
        Long userId = null==sysUsers.get(0)?null:sysUsers.get(0).getId();
        Customer customer = new Customer(1,userId,LocalDateTime.now(), SessionUtils.getUserId(),LocalDateTime.now()
                ,callTimeStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),userCode,remark,customerId);
        //录入客户表
        customerService.insert(customer);
        return "1";
    }
}

