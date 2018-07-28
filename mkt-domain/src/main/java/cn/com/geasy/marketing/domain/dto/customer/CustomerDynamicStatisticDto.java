/*
 * Copyright 2016-2018 the original author or authors.
 * Created on 2018/7/24 0024 16:15
 */
package cn.com.geasy.marketing.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户行为统计Dto
 *
 * @author worso
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDynamicStatisticDto implements Serializable {

    private static final long serialVersionUID = -3326736238121607672L;
    /**
     * 事件(0=阅读,1=订阅,2=联系)
     */
    private Integer event;
    /**
     * 事件统计次数
     */
    private Integer count;
}
