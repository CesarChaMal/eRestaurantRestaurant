package com.erestaurant.restautant.service.impl;

import com.erestaurant.restautant.domain.RestaurantDiscount;
import com.erestaurant.restautant.repository.RestaurantDiscountRepository;
import com.erestaurant.restautant.service.RestaurantDiscountService;
import com.erestaurant.restautant.service.dto.RestaurantDiscountDTO;
import com.erestaurant.restautant.service.mapper.RestaurantDiscountMapper;
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
 * Service Implementation for managing {@link RestaurantDiscount}.
 */
@Service
@Transactional
public class RestaurantDiscountServiceImpl implements RestaurantDiscountService {

    private final Logger log = LoggerFactory.getLogger(RestaurantDiscountServiceImpl.class);

    private final RestaurantDiscountRepository restaurantDiscountRepository;

    private final RestaurantDiscountMapper restaurantDiscountMapper;

    public RestaurantDiscountServiceImpl(
        RestaurantDiscountRepository restaurantDiscountRepository,
        RestaurantDiscountMapper restaurantDiscountMapper
    ) {
        this.restaurantDiscountRepository = restaurantDiscountRepository;
        this.restaurantDiscountMapper = restaurantDiscountMapper;
    }

    @Override
    public Mono<RestaurantDiscountDTO> save(RestaurantDiscountDTO restaurantDiscountDTO) {
        log.debug("Request to save RestaurantDiscount : {}", restaurantDiscountDTO);
        return restaurantDiscountRepository
            .save(restaurantDiscountMapper.toEntity(restaurantDiscountDTO))
            .map(restaurantDiscountMapper::toDto);
    }

    @Override
    public Mono<RestaurantDiscountDTO> update(RestaurantDiscountDTO restaurantDiscountDTO) {
        log.debug("Request to save RestaurantDiscount : {}", restaurantDiscountDTO);
        return restaurantDiscountRepository
            .save(restaurantDiscountMapper.toEntity(restaurantDiscountDTO).setIsPersisted())
            .map(restaurantDiscountMapper::toDto);
    }

    @Override
    public Mono<RestaurantDiscountDTO> partialUpdate(RestaurantDiscountDTO restaurantDiscountDTO) {
        log.debug("Request to partially update RestaurantDiscount : {}", restaurantDiscountDTO);

        return restaurantDiscountRepository
            .findById(restaurantDiscountDTO.getId())
            .map(existingRestaurantDiscount -> {
                restaurantDiscountMapper.partialUpdate(existingRestaurantDiscount, restaurantDiscountDTO);

                return existingRestaurantDiscount;
            })
            .flatMap(restaurantDiscountRepository::save)
            .map(restaurantDiscountMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<RestaurantDiscountDTO> findAll() {
        log.debug("Request to get all RestaurantDiscounts");
        return restaurantDiscountRepository.findAll().map(restaurantDiscountMapper::toDto);
    }

    public Mono<Long> countAll() {
        return restaurantDiscountRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<RestaurantDiscountDTO> findOne(String id) {
        log.debug("Request to get RestaurantDiscount : {}", id);
        return restaurantDiscountRepository.findById(id).map(restaurantDiscountMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete RestaurantDiscount : {}", id);
        return restaurantDiscountRepository.deleteById(id);
    }
}
