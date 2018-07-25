package cn.com.geasy.marketing.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户动态Dto
 * @author tingfei.wang
 * @date 2018年07月22日 10:20:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDynamicDto  implements Serializable{

    private static final long serialVersionUID = 5196270173475692183L;
    private Long id;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 事件(0=阅读,1=订阅,2=联系)
     */
    private Integer event;
    /**
     * 发生日期
     */
    private LocalDateTime eventDate;
    /**
     * 文章ID
     */
    private Long articleId;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文章标签
     */
    private String articleTag;
    /**
     * 阅读时长/秒
     */
    private Integer readTime;
    /**
     * 是否阅读全文
     * （0=否，1=是）
     */
    private Integer isFullRead;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 微信头像URL
     */
    private String headImgUrl;

}
