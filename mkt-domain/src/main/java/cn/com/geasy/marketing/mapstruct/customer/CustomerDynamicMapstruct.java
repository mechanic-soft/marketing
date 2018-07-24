/**
 * @author tingfei.wang
 * @date 2018年07月22日 15:02:00
 */
package cn.com.geasy.marketing.mapstruct.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerDynamicDto;
import cn.com.geasy.marketing.domain.entity.customer.CustomerDynamic;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * CustomerDynamic 与 CustomerDynamicDto 互转
 * @author tingfei.wang
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDynamicMapstruct {

    CustomerDynamicMapstruct getInstance = Mappers.getMapper(CustomerDynamicMapstruct.class);

    CustomerDynamic toEntity(CustomerDynamicDto dto);

    List<CustomerDynamic> toEntityList(List<CustomerDynamicDto> dtoList);

    CustomerDynamicDto toDto(CustomerDynamic domain);

    List<CustomerDynamicDto> toDtoList(List<CustomerDynamic> domainList);

}