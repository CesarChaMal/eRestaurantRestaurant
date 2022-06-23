package com.erestaurant.restautant.web.rest;

import com.erestaurant.restautant.repository.RestaurantRepository;
import com.erestaurant.restautant.service.RestaurantService;
import com.erestaurant.restautant.service.dto.RestaurantDTO;
import com.erestaurant.restautant.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.erestaurant.restautant.domain.Restaurant}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantResource.class);

    private static final String ENTITY_NAME = "eRestaurantRestaurantRestaurant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantService restaurantService;

    private final RestaurantRepository restaurantRepository;

    public RestaurantResource(RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * {@code POST  /restaurants} : Create a new restaurant.
     *
     * @param restaurantDTO the restaurantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantDTO, or with status {@code 400 (Bad Request)} if the restaurant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurants")
    public Mono<ResponseEntity<RestaurantDTO>> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {
        log.debug("REST request to save Restaurant : {}", restaurantDTO);
        if (restaurantDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return restaurantService
            .save(restaurantDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/restaurants/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /restaurants/:id} : Updates an existing restaurant.
     *
     * @param id the id of the restaurantDTO to save.
     * @param restaurantDTO the restaurantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurants/{id}")
    public Mono<ResponseEntity<RestaurantDTO>> updateRestaurant(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody RestaurantDTO restaurantDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Restaurant : {}, {}", id, restaurantDTO);
        if (restaurantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return restaurantRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return restaurantService
                    .update(restaurantDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /restaurants/:id} : Partial updates given fields of an existing restaurant, field will ignore if it is null
     *
     * @param id the id of the restaurantDTO to save.
     * @param restaurantDTO the restaurantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurants/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<RestaurantDTO>> partialUpdateRestaurant(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody RestaurantDTO restaurantDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Restaurant partially : {}, {}", id, restaurantDTO);
        if (restaurantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return restaurantRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<RestaurantDTO> result = restaurantService.partialUpdate(restaurantDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /restaurants} : get all the restaurants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurants in body.
     */
    @GetMapping("/restaurants")
    public Mono<List<RestaurantDTO>> getAllRestaurants() {
        log.debug("REST request to get all Restaurants");
        return restaurantService.findAll().collectList();
    }

    /**
     * {@code GET  /restaurants} : get all the restaurants as a stream.
     * @return the {@link Flux} of restaurants.
     */
    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<RestaurantDTO> getAllRestaurantsAsStream() {
        log.debug("REST request to get all Restaurants as a stream");
        return restaurantService.findAll();
    }

    /**
     * {@code GET  /restaurants/:id} : get the "id" restaurant.
     *
     * @param id the id of the restaurantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurants/{id}")
    public Mono<ResponseEntity<RestaurantDTO>> getRestaurant(@PathVariable String id) {
        log.debug("REST request to get Restaurant : {}", id);
        Mono<RestaurantDTO> restaurantDTO = restaurantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantDTO);
    }

    /**
     * {@code DELETE  /restaurants/:id} : delete the "id" restaurant.
     *
     * @param id the id of the restaurantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurants/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteRestaurant(@PathVariable String id) {
        log.debug("REST request to delete Restaurant : {}", id);
        return restaurantService
            .delete(id)
            .map(result ->
                ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
            );
    }
}
