package cn.com.geasy.marketing.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户动态DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDynamicDto implements Serializable {
    private static final long serialVersionUID = -7434998367213285393L;
    /**
     * 登录账户
     */
    private Long customerId;
    /**
     * 事件
     */
    private String event;
    /**
     * 发生日期
     */
    private String eventDate;
    /**
     * 文章ID
     */
    private String articleId;
    /**
     * 文章标题
     */
    private Long articleTitle;
    /**
     * 用户ID
     */
    private String userId;
}
