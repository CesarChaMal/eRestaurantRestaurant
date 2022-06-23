package com.erestaurant.restautant.repository;

import com.erestaurant.restautant.domain.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the Restaurant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestaurantRepository extends ReactiveCrudRepository<Restaurant, String>, RestaurantRepositoryInternal {
    @Override
    <S extends Restaurant> Mono<S> save(S entity);

    @Override
    Flux<Restaurant> findAll();

    @Override
    Mono<Restaurant> findById(String id);

    @Override
    Mono<Void> deleteById(String id);
}

interface RestaurantRepositoryInternal {
    <S extends Restaurant> Mono<S> save(S entity);

    Flux<Restaurant> findAllBy(Pageable pageable);

    Flux<Restaurant> findAll();

    Mono<Restaurant> findById(String id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Restaurant> findAllBy(Pageable pageable, Criteria criteria);

}
