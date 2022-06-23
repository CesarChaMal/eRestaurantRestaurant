package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.Restaurant;
import com.erestaurant.restautant.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restaurant} and its DTO {@link RestaurantDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {}
