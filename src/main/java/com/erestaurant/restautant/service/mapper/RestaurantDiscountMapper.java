package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.RestaurantDiscount;
import com.erestaurant.restautant.service.dto.RestaurantDiscountDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantDiscount} and its DTO {@link RestaurantDiscountDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantDiscountMapper extends EntityMapper<RestaurantDiscountDTO, RestaurantDiscount> {}
