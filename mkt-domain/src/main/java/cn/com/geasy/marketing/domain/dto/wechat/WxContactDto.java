package cn.com.geasy.marketing.domain.dto.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信联系人Dto
 *
 * @author yml
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxContactDto implements Serializable{

    private static final long serialVersionUID = 5458432260549192749L;
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
