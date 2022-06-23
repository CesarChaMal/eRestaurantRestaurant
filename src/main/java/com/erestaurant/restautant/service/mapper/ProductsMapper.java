package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.Products;
import com.erestaurant.restautant.service.dto.ProductsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Products} and its DTO {@link ProductsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductsMapper extends EntityMapper<ProductsDTO, Products> {}
