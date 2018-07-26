package cn.com.geasy.marketing.service.externalcall;

import cn.com.geasy.marketing.domain.dto.externalCall.QuestionnaireDto;
import cn.com.geasy.marketing.domain.entity.externalCall.ExternalCall;
import com.gitee.mechanic.mybatis.base.SuperService;

import java.util.List;

/**
 * 外呼接口Service
 */
public interface ExternalCallService extends SuperService<ExternalCall> {

    public String insert(ExternalCall ec, List<QuestionnaireDto> record);
}
