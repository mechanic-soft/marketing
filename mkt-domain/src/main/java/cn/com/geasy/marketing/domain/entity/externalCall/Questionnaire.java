package cn.com.geasy.marketing.domain.entity.externalCall;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 问卷记录实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("questionnaire")
public class Questionnaire extends Entity implements Serializable {
    private static final long serialVersionUID = 267791232215577189L;

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