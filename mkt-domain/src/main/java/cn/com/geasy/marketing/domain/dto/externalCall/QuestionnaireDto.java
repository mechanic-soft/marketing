package cn.com.geasy.marketing.domain.dto.externalCall;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 问卷记录DTO
 */
@Data
public class QuestionnaireDto implements Serializable {
    private static final long serialVersionUID = 5823555947842473318L;

    /**
     * 问卷序号
     */
    private Long sequenceNumber;

    /**
     * 是否必填
     */
    private Long isNotNull;

    /**
     * 问卷内容
     */
    private String content;

    /**
     * 问卷结果
     */
    private String result;

    /**
     * 备注
     */
    private String remark;


}