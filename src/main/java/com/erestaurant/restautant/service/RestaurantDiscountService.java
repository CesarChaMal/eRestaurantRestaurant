package com.erestaurant.restautant.service;

import com.erestaurant.restautant.service.dto.RestaurantDiscountDTO;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.erestaurant.restautant.domain.RestaurantDiscount}.
 */
public interface RestaurantDiscountService {
    /**
     * Save a restaurantDiscount.
     *
     * @param restaurantDiscountDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<RestaurantDiscountDTO> save(RestaurantDiscountDTO restaurantDiscountDTO);

    /**
     * Updates a restaurantDiscount.
     *
     * @param restaurantDiscountDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<RestaurantDiscountDTO> update(RestaurantDiscountDTO restaurantDiscountDTO);

    /**
     * Partially updates a restaurantDiscount.
     *
     * @param restaurantDiscountDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<RestaurantDiscountDTO> partialUpdate(RestaurantDiscountDTO restaurantDiscountDTO);

    /**
     * Get all the restaurantDiscounts.
     *
     * @return the list of entities.
     */
    Flux<RestaurantDiscountDTO> findAll();

    /**
     * Returns the number of restaurantDiscounts available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" restaurantDiscount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<RestaurantDiscountDTO> findOne(String id);

    /**
     * Delete the "id" restaurantDiscount.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
