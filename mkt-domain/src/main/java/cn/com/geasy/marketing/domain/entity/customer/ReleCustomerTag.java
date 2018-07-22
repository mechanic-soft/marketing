package cn.com.geasy.marketing.domain.entity.customer;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gitee.mechanic.mybatis.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by yml on 2018/7/22.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("rele_customer_tag")
public class ReleCustomerTag extends Entity implements Serializable{
    /**
     *客户ID
     */
    private Long customerId;
    /**
     *系统标签ID
     */
    private Long tagId;
}
