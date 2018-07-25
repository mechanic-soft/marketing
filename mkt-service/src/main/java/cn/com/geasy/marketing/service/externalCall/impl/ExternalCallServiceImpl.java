package cn.com.geasy.marketing.service.externalCall.impl;

import cn.com.geasy.marketing.dao.ExternalCall.ExternalCallMapper;
import cn.com.geasy.marketing.domain.dto.externalCall.QuestionnaireDto;
import cn.com.geasy.marketing.domain.dto.system.SysLogDto;
import cn.com.geasy.marketing.domain.entity.externalCall.ExternalCall;
import cn.com.geasy.marketing.domain.entity.externalCall.Questionnaire;
import cn.com.geasy.marketing.domain.entity.externalCall.ReleExternalCallQuestionnaire;
import cn.com.geasy.marketing.mapstruct.externalCall.QuestionnaireMapstruct;
import cn.com.geasy.marketing.mapstruct.system.SysLogMapstruct;
import cn.com.geasy.marketing.service.externalCall.ExternalCallService;
import cn.com.geasy.marketing.service.externalCall.QuestionnaireService;
import cn.com.geasy.marketing.service.externalCall.ReleExternalCallQuestionnaireService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ExternalCallServiceImpl extends SuperServiceImpl<ExternalCallMapper, ExternalCall> implements ExternalCallService {

    @Autowired
    private QuestionnaireService questionSrv;

    @Autowired
    private ReleExternalCallQuestionnaireService recqSrv;



    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public String insert(ExternalCall ec, List<QuestionnaireDto> record) {

        ec.setCreateTime(LocalDateTime.now());
        ec.setCreateUser(1L);//admin
        ec.setUpdateUser(1L);
        this.insert(ec);

        Long externalCallId = ec.getId();
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

        return "1";
    }
}

