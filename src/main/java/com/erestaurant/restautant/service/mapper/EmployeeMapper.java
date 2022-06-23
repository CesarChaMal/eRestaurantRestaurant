package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.Employee;
import com.erestaurant.restautant.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {}
