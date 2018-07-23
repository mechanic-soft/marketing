package cn.com.geasy.marketing.domain.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 客户DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -8552790719319027725L;


    /**昵称*/
    private String nickname;

    /**微信用户头像URL*/
    private String headImgUrl;

    /**标签id*/
    private List<Long> tagIds;

    /**是否加微信  1是  0否*/
    private Integer isAddWechat;

    /**最近联系*/
    private String lastContact;

    /**是否开户（0=否，1=是）*/
    private Integer isOpenAccount;

    /**主键*/
    private Long id;

    private LocalDate callTime;

    /**外呼时间 开始*/
    private LocalDate callTimeStart;

    /**外呼时间  结束*/
    private LocalDate callTimeEnd;

    /**微信号*/
    private String wxId;

    /**备注*/
    private String remark;

    /**手机号*/
    private Long phone;

    /**成熟度*/
    private Integer maturity;

    /**微信联系人ID*/
    private String wxContactId;

    public CustomerDto(String nickname, Integer isAddWechat, List<Long> tagIds, LocalDate callTimeStart, LocalDate callTimeEnd) {
        this.nickname = nickname;
        this.tagIds = tagIds;
        this.isAddWechat = isAddWechat;
        this.callTimeStart = callTimeStart;
        this.callTimeEnd = callTimeEnd;
    }
    @JsonIgnore
    public String getCallTimeStartStr(){
        return null!=callTimeStart?callTimeStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):null;
    }
    @JsonIgnore
    public String getCallTimeEndStr(){

        return  null != callTimeEnd?callTimeEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")):null;
    }
}
