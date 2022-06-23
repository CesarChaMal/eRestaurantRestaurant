package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.Categories;
import com.erestaurant.restautant.service.dto.CategoriesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categories} and its DTO {@link CategoriesDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoriesMapper extends EntityMapper<CategoriesDTO, Categories> {}
