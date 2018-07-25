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
 * 外呼实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("external_call")
public class ExternalCall extends Entity implements Serializable {
    private static final long serialVersionUID = 267791232213577189L;

    /**
     * 项目编码
     */
    private String itemCode;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 任务编码
     */
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 营业厅编码
     */
    private String businessHallCode;

    /**
     * 营业厅名称
     */
    private String businessHallName;

    /**
     * 外呼时间
     */
    private LocalDateTime callTime;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 坐席工号（客户经理id）
     */
    private String customerId;

    /**
     * 坐席工号名称（客户经理名称）
     */
    private String customerName;

    /**
     * 呼叫开始时间
     */
    private LocalDateTime callTimeStart;

    /**
     * 呼叫应答时间
     */
    private LocalDateTime callTimeAnswer;

    /**
     * 呼叫结束时间
     */
    private LocalDateTime callTimeEnd;

    /**
     * 通话时长
     */
    private Long talkTime;

    /**
     * 振铃时长
     */
    private Long ringTime;

    /**
     * 排队时长
     */
    private Long lineUpTime;

    /**
     * 是否接通
     */
    private Integer isAnswer;

    /**
     * 呼叫结果(用户挂机、用户未接通)
     */
    private String callResult;

    /**
     * 呼叫类别(1呼、2呼、3呼)
     */
    private String callType;

    /**
     * 呼叫方向(呼出、呼入)
     */
    private String callInOut;

    /**
     * 是否下发短信
     */
    private String isSendMsg;

    /**
     * 短信内容
     */
    private String msg;

    /**
     * 录音内容
     */
    private String recordMsg;

    /**
     * 预留1
     */
    private String reservedFieldOne;

    /**
     * 预留2
     */
    private String reservedFieldTwo;

    /**
     * 预留3
     */
    private String reservedFieldThree;

    /**
     * 预留4
     */
    private String reservedFieldFour;

    /**
     * 预留5
     */
    private String reservedFieldFive;

    /**
     * 备注
     */
    private String remark;

}
