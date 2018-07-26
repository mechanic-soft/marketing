package cn.com.geasy.marketing.api.externalcall;

import cn.com.geasy.marketing.domain.dto.externalCall.QuestionnaireDto;
import cn.com.geasy.marketing.domain.entity.externalCall.ExternalCall;
import cn.com.geasy.marketing.service.externalcall.ExternalCallService;
import com.gitee.mechanic.web.utils.ResponseUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 外呼系统数据接入
 */
@Api(tags = "ExternallCall", description = "外呼系统接口")
@Slf4j
@RestController
@RequestMapping(path = "/v1/externallCall")
public class ExternallCallController {

    @Autowired
    private ExternalCallService ecSrv;


    @ApiOperation(value = "外呼系统数据接入")
    @PostMapping(path = "/externallCalls", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ModelMap> releWx(
            @ApiParam("项目编码")@RequestParam(required = false) String itemCode,
            @ApiParam("项目名称")@RequestParam(required = false) String itemName,
            @ApiParam("任务编码")@RequestParam(required = false) String taskCode,
            @ApiParam("任务名称")@RequestParam(required = false) String taskName,
            @ApiParam("营业厅编码")@RequestParam(required = false) String businessHallCode,
            @ApiParam("营业厅名称")@RequestParam(required = false) String businessHallName,
            @ApiParam("外呼时间，日期格式为 yyyy-MM-dd HH:mm:ss") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(required = false) LocalDateTime callTime,
            @ApiParam("用户编码")@RequestParam(required = false) String userCode,
            @ApiParam("客户经理Id")@RequestParam(required = false) String customerId,
            @ApiParam("客户经理名称")@RequestParam(required = false) String customerName,
            @ApiParam("呼叫开始时间，日期格式为 yyyy-MM-dd HH:mm:ss") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(required = false) LocalDateTime callTimeStart,
            @ApiParam("呼叫应答时间，日期格式为 yyyy-MM-dd HH:mm:ss") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(required = false) LocalDateTime callTimeAnswer,
            @ApiParam("呼叫结束时间，日期格式为 yyyy-MM-dd HH:mm:ss") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(required = false) LocalDateTime callTimeEnd,
            @ApiParam("通话时长（数字）")@RequestParam(required = false) Long talkTime,
            @ApiParam("振铃时长（数字）")@RequestParam(required = false) Long ringTime,
            @ApiParam("排队时长（数字）")@RequestParam(required = false) Long lineUpTime,
            @ApiParam("是否接通 (1是；0否)")@RequestParam(required = false) Integer isAnswer,
            @ApiParam("呼叫类别（1呼,2呼,3呼）")@RequestParam(required = false) String callType,
            @ApiParam("呼叫结果")@RequestParam(required = false)String callResult,
            @ApiParam("呼叫方向")@RequestParam(required = false) String callInOut,
            @ApiParam("是否下发短信")@RequestParam(required = false) String isSendMsg,
            @ApiParam("短信内容")@RequestParam(required = false) String msg,
            @ApiParam("录音内容")@RequestParam(required = false) String recordMsg,
            @ApiParam("预留1")@RequestParam(required = false) String reservedFieldOne,
            @ApiParam("预留2")@RequestParam(required = false) String reservedFieldTwo,
            @ApiParam("预留3")@RequestParam(required = false) String reservedFieldThree,
            @ApiParam("预留4")@RequestParam(required = false) String reservedFieldFour,
            @ApiParam("预留5")@RequestParam(required = false) String reservedFieldFive,
            @ApiParam("备注")@RequestParam(required = false) String remark,
            @ApiParam("问卷记录")@RequestBody(required = false) List<QuestionnaireDto> record
      ){
        ExternalCall ec = new ExternalCall(itemCode,itemName,taskCode,taskName,businessHallCode,businessHallName,callTime,userCode,customerId,customerName,callTimeStart,callTimeAnswer,callTimeEnd,talkTime,ringTime,lineUpTime,isAnswer,callResult,callType,callInOut,isSendMsg,msg,recordMsg,reservedFieldOne,reservedFieldTwo,reservedFieldThree,reservedFieldFour,reservedFieldFive,remark);
        String flag = "0";

        flag = ecSrv.insert(ec,record);
        return ResponseUtils.result(flag);

    }

}
