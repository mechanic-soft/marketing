package cn.com.geasy.marketing.domain.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private static final long serialVersionUID = 6532714755800499404L;
    /**
     * 事件(0=阅读,1=订阅,2=联系,3=转发,4=加微,5=开户,6=呼叫)
     */
    private Integer event;

    /**
     * 事件日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime eventDate;

}
