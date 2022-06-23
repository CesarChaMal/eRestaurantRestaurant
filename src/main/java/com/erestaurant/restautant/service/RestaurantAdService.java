package com.erestaurant.restautant.service;

import com.erestaurant.restautant.service.dto.RestaurantAdDTO;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.erestaurant.restautant.domain.RestaurantAd}.
 */
public interface RestaurantAdService {
    /**
     * Save a restaurantAd.
     *
     * @param restaurantAdDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<RestaurantAdDTO> save(RestaurantAdDTO restaurantAdDTO);

    /**
     * Updates a restaurantAd.
     *
     * @param restaurantAdDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<RestaurantAdDTO> update(RestaurantAdDTO restaurantAdDTO);

    /**
     * Partially updates a restaurantAd.
     *
     * @param restaurantAdDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<RestaurantAdDTO> partialUpdate(RestaurantAdDTO restaurantAdDTO);

    /**
     * Get all the restaurantAds.
     *
     * @return the list of entities.
     */
    Flux<RestaurantAdDTO> findAll();

    /**
     * Returns the number of restaurantAds available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" restaurantAd.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<RestaurantAdDTO> findOne(String id);

    /**
     * Delete the "id" restaurantAd.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
