package com.erestaurant.restautant.service.impl;

import com.erestaurant.restautant.domain.RestaurantAd;
import com.erestaurant.restautant.repository.RestaurantAdRepository;
import com.erestaurant.restautant.service.RestaurantAdService;
import com.erestaurant.restautant.service.dto.RestaurantAdDTO;
import com.erestaurant.restautant.service.mapper.RestaurantAdMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link RestaurantAd}.
 */
@Service
@Transactional
public class RestaurantAdServiceImpl implements RestaurantAdService {

    private final Logger log = LoggerFactory.getLogger(RestaurantAdServiceImpl.class);

    private final RestaurantAdRepository restaurantAdRepository;

    private final RestaurantAdMapper restaurantAdMapper;

    public RestaurantAdServiceImpl(RestaurantAdRepository restaurantAdRepository, RestaurantAdMapper restaurantAdMapper) {
        this.restaurantAdRepository = restaurantAdRepository;
        this.restaurantAdMapper = restaurantAdMapper;
    }

    @Override
    public Mono<RestaurantAdDTO> save(RestaurantAdDTO restaurantAdDTO) {
        log.debug("Request to save RestaurantAd : {}", restaurantAdDTO);
        return restaurantAdRepository.save(restaurantAdMapper.toEntity(restaurantAdDTO)).map(restaurantAdMapper::toDto);
    }

    @Override
    public Mono<RestaurantAdDTO> update(RestaurantAdDTO restaurantAdDTO) {
        log.debug("Request to save RestaurantAd : {}", restaurantAdDTO);
        return restaurantAdRepository.save(restaurantAdMapper.toEntity(restaurantAdDTO).setIsPersisted()).map(restaurantAdMapper::toDto);
    }

    @Override
    public Mono<RestaurantAdDTO> partialUpdate(RestaurantAdDTO restaurantAdDTO) {
        log.debug("Request to partially update RestaurantAd : {}", restaurantAdDTO);

        return restaurantAdRepository
            .findById(restaurantAdDTO.getId())
            .map(existingRestaurantAd -> {
                restaurantAdMapper.partialUpdate(existingRestaurantAd, restaurantAdDTO);

                return existingRestaurantAd;
            })
            .flatMap(restaurantAdRepository::save)
            .map(restaurantAdMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<RestaurantAdDTO> findAll() {
        log.debug("Request to get all RestaurantAds");
        return restaurantAdRepository.findAll().map(restaurantAdMapper::toDto);
    }

    public Mono<Long> countAll() {
        return restaurantAdRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<RestaurantAdDTO> findOne(String id) {
        log.debug("Request to get RestaurantAd : {}", id);
        return restaurantAdRepository.findById(id).map(restaurantAdMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete RestaurantAd : {}", id);
        return restaurantAdRepository.deleteById(id);
    }
}
