package cn.com.geasy.marketing.domain.entity.customer;

/**
 * Created by yml on 2018/7/22.
 */

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户生命周期事件
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("customer_lifecycle_event")
public class CustomerLlifecycleEvent extends Entity implements Serializable {
    /**
     * 客户ID
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long customerId;
    /**
     * 事件(0=呼叫,1=加微,2=转发,3=开户,4=阅读,5=联系)
     */
    private Integer event;
    /**
     * 用户ID
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;
    /**
     * 事件日期
     */
    private LocalDateTime eventDate;
}
