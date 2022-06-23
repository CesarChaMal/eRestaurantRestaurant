package com.erestaurant.restautant.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantDiscountMapperTest {

    private RestaurantDiscountMapper restaurantDiscountMapper;

    @BeforeEach
    public void setUp() {
        restaurantDiscountMapper = new RestaurantDiscountMapperImpl();
    }
}
