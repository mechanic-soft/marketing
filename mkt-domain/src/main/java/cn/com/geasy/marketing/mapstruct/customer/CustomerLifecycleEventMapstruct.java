package cn.com.geasy.marketing.mapstruct.customer;

import cn.com.geasy.marketing.domain.dto.customer.CustomerLifecycleEventDto;
import cn.com.geasy.marketing.domain.dto.system.SysCorpDto;
import cn.com.geasy.marketing.domain.entity.customer.CustomerLifecycleEvent;
import cn.com.geasy.marketing.domain.entity.system.SysCorp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 请在此写下该类的说明
 *
 * @author yml
 * @version 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerLifecycleEventMapstruct {
    CustomerLifecycleEventMapstruct getInstance = Mappers.getMapper(CustomerLifecycleEventMapstruct.class);

    CustomerLifecycleEvent toEntity(CustomerLifecycleEventDto dto);

    List<CustomerLifecycleEvent> toEntityList(List<CustomerLifecycleEventDto> dtoList);

    CustomerLifecycleEventDto toDto(CustomerLifecycleEvent domain);

    List<CustomerLifecycleEventDto> toDtoList(List<CustomerLifecycleEvent> domainList);
}
