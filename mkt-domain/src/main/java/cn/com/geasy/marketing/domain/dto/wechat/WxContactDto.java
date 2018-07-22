package cn.com.geasy.marketing.domain.dto.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yml on 2018/7/20.
 */

/**
 * 微信联系人DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxContactDto {
    /**
     *
     */
    private Long id;
    /**
     *
     */
    private Integer isSync;
    /**
     *
     */
    private String nickname;
}
