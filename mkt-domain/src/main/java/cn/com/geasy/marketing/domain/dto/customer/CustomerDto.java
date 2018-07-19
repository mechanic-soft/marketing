package cn.com.geasy.marketing.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
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
    private String wxHeadImgUrl;

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

    /**外呼时间*/
    private Date callTime;

    /**微信号*/
    private String wxId;

    /**备注*/
    private String remark;

    /**手机号*/
    private Long phone;

    /**成熟度*/
    private Integer maturity;

}
