package cn.com.geasy.marketing.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 请在此写下该类的说明
 *
 * @author yml
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLifecycleEventDto implements Serializable {
    /**
     * 事件(0=呼叫,1=加微,2=转发,3=开户,4=阅读,5=联系)
     */
    private Integer event;

    /**
     * 事件日期
     */
    private LocalDateTime eventDate;
}
