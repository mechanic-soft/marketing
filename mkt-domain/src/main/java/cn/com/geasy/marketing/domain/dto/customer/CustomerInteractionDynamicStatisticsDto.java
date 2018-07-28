/**
 * @author tingfei.wang
 * @date 2018年07月22日 19:23:00
 */
package cn.com.geasy.marketing.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 客户互动动态统计Dto
 * @author tingfei.wang
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInteractionDynamicStatisticsDto  implements Serializable {

    private static final long serialVersionUID = -2430772615333862856L;

    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 微信头像URL
     */
    private String headImgUrl;
    /**
     * 互动总次数
     * (只包含阅读和聊天)
     */
    private Integer interactionCount;
    /**
     * 互动次数分类统计
     */
    private List<CustomerDynamicStatisticDto> interaction;
}