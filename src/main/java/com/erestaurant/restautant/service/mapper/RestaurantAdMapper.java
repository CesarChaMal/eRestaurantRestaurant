package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.RestaurantAd;
import com.erestaurant.restautant.service.dto.RestaurantAdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantAd} and its DTO {@link RestaurantAdDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantAdMapper extends EntityMapper<RestaurantAdDTO, RestaurantAd> {}
