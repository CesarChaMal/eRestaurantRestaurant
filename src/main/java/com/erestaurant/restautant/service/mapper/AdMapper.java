package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.Ad;
import com.erestaurant.restautant.service.dto.AdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ad} and its DTO {@link AdDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdMapper extends EntityMapper<AdDTO, Ad> {}
