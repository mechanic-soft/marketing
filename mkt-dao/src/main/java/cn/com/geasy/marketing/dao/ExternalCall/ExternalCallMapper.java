package cn.com.geasy.marketing.dao.ExternalCall;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDto;
import cn.com.geasy.marketing.domain.entity.customer.Customer;
import cn.com.geasy.marketing.domain.entity.externalCall.ExternalCall;
import com.baomidou.mybatisplus.plugins.Page;
import com.gitee.mechanic.mybatis.base.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 外呼系统Mapper
 */
public interface ExternalCallMapper extends SuperMapper<ExternalCall> {


}

