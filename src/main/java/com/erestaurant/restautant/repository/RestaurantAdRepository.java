package com.erestaurant.restautant.repository;

import com.erestaurant.restautant.domain.RestaurantAd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the RestaurantAd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestaurantAdRepository extends ReactiveCrudRepository<RestaurantAd, String>, RestaurantAdRepositoryInternal {
    @Override
    <S extends RestaurantAd> Mono<S> save(S entity);

    @Override
    Flux<RestaurantAd> findAll();

    @Override
    Mono<RestaurantAd> findById(String id);

    @Override
    Mono<Void> deleteById(String id);
}

interface RestaurantAdRepositoryInternal {
    <S extends RestaurantAd> Mono<S> save(S entity);

    Flux<RestaurantAd> findAllBy(Pageable pageable);

    Flux<RestaurantAd> findAll();

    Mono<RestaurantAd> findById(String id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<RestaurantAd> findAllBy(Pageable pageable, Criteria criteria);

}
