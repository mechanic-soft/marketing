/**
 * @author tingfei.wang
 * @date 2018年07月22日 18:51:00
 */
package cn.com.geasy.marketing.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户文章阅读动态统计Dto
 * @author tingfei.wang
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerArticleDynamicStatisticsDto implements Serializable {

    private static final long serialVersionUID = 7106501376124674716L;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 阅读客户数
     */
    private Integer readCustomerCount;
    /**
     * 全文阅读客户数
     */
    private Integer fullReadCustomerCount;
    /**
     * 平均阅读时长/秒
     */
    private Integer averageReadSeconds;

}