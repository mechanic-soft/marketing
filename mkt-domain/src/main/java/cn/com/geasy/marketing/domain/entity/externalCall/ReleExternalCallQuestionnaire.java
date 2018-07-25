package cn.com.geasy.marketing.domain.entity.externalCall;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 外呼与问卷记录关联实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("rele_external_call_questionnaire")
public class ReleExternalCallQuestionnaire extends Entity implements Serializable {
    private static final long serialVersionUID = 267744442215577189L;



    /**
     * 外呼信息id
     */
    private Long externalCallId;

    /**
     * 问卷记录id
     */
    private Long questionnaireId;


    public ReleExternalCallQuestionnaire(Long externalCallId, Long questionnaireId, LocalDateTime createTime, LocalDateTime updateTime, long createUser, long updateUser) {
        this.externalCallId = externalCallId;
        this.questionnaireId = questionnaireId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
}