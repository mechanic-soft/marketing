package cn.com.geasy.marketing.service.externalCall.impl;

import cn.com.geasy.marketing.dao.ExternalCall.ExternalCallMapper;
import cn.com.geasy.marketing.dao.ExternalCall.QuestionnaireMapper;
import cn.com.geasy.marketing.domain.dto.externalCall.QuestionnaireDto;
import cn.com.geasy.marketing.domain.entity.externalCall.ExternalCall;
import cn.com.geasy.marketing.domain.entity.externalCall.Questionnaire;
import cn.com.geasy.marketing.service.externalCall.ExternalCallService;
import cn.com.geasy.marketing.service.externalCall.QuestionnaireService;
import com.gitee.mechanic.mybatis.base.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class QuestionnaireServiceImpl extends SuperServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {



}

