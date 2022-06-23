package com.erestaurant.restautant.repository;

import com.erestaurant.restautant.domain.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the Notification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationRepository extends ReactiveCrudRepository<Notification, String>, NotificationRepositoryInternal {
    @Override
    <S extends Notification> Mono<S> save(S entity);

    @Override
    Flux<Notification> findAll();

    @Override
    Mono<Notification> findById(String id);

    @Override
    Mono<Void> deleteById(String id);
}

interface NotificationRepositoryInternal {
    <S extends Notification> Mono<S> save(S entity);

    Flux<Notification> findAllBy(Pageable pageable);

    Flux<Notification> findAll();

    Mono<Notification> findById(String id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Notification> findAllBy(Pageable pageable, Criteria criteria);

}
