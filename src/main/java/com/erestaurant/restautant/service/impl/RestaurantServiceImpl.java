package com.erestaurant.restautant.service.impl;

import com.erestaurant.restautant.domain.Restaurant;
import com.erestaurant.restautant.repository.RestaurantRepository;
import com.erestaurant.restautant.service.RestaurantService;
import com.erestaurant.restautant.service.dto.RestaurantDTO;
import com.erestaurant.restautant.service.mapper.RestaurantMapper;
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
 * Service Implementation for managing {@link Restaurant}.
 */
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public Mono<RestaurantDTO> save(RestaurantDTO restaurantDTO) {
        log.debug("Request to save Restaurant : {}", restaurantDTO);
        return restaurantRepository.save(restaurantMapper.toEntity(restaurantDTO)).map(restaurantMapper::toDto);
    }

    @Override
    public Mono<RestaurantDTO> update(RestaurantDTO restaurantDTO) {
        log.debug("Request to save Restaurant : {}", restaurantDTO);
        return restaurantRepository.save(restaurantMapper.toEntity(restaurantDTO).setIsPersisted()).map(restaurantMapper::toDto);
    }

    @Override
    public Mono<RestaurantDTO> partialUpdate(RestaurantDTO restaurantDTO) {
        log.debug("Request to partially update Restaurant : {}", restaurantDTO);

        return restaurantRepository
            .findById(restaurantDTO.getId())
            .map(existingRestaurant -> {
                restaurantMapper.partialUpdate(existingRestaurant, restaurantDTO);

                return existingRestaurant;
            })
            .flatMap(restaurantRepository::save)
            .map(restaurantMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<RestaurantDTO> findAll() {
        log.debug("Request to get all Restaurants");
        return restaurantRepository.findAll().map(restaurantMapper::toDto);
    }

    public Mono<Long> countAll() {
        return restaurantRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<RestaurantDTO> findOne(String id) {
        log.debug("Request to get Restaurant : {}", id);
        return restaurantRepository.findById(id).map(restaurantMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Restaurant : {}", id);
        return restaurantRepository.deleteById(id);
    }
}
