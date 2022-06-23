package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.Discount;
import com.erestaurant.restautant.service.dto.DiscountDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Discount} and its DTO {@link DiscountDTO}.
 */
@Mapper(componentModel = "spring")
public interface DiscountMapper extends EntityMapper<DiscountDTO, Discount> {}
