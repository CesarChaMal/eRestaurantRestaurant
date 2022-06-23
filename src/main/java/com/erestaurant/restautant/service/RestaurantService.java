package com.erestaurant.restautant.service;

import com.erestaurant.restautant.service.dto.RestaurantDTO;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.erestaurant.restautant.domain.Restaurant}.
 */
public interface RestaurantService {
    /**
     * Save a restaurant.
     *
     * @param restaurantDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<RestaurantDTO> save(RestaurantDTO restaurantDTO);

    /**
     * Updates a restaurant.
     *
     * @param restaurantDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<RestaurantDTO> update(RestaurantDTO restaurantDTO);

    /**
     * Partially updates a restaurant.
     *
     * @param restaurantDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<RestaurantDTO> partialUpdate(RestaurantDTO restaurantDTO);

    /**
     * Get all the restaurants.
     *
     * @return the list of entities.
     */
    Flux<RestaurantDTO> findAll();

    /**
     * Returns the number of restaurants available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" restaurant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<RestaurantDTO> findOne(String id);

    /**
     * Delete the "id" restaurant.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
