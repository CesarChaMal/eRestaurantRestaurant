package com.erestaurant.restautant.service.impl;

import com.erestaurant.restautant.domain.Products;
import com.erestaurant.restautant.repository.ProductsRepository;
import com.erestaurant.restautant.service.ProductsService;
import com.erestaurant.restautant.service.dto.ProductsDTO;
import com.erestaurant.restautant.service.mapper.ProductsMapper;
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
 * Service Implementation for managing {@link Products}.
 */
@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private final Logger log = LoggerFactory.getLogger(ProductsServiceImpl.class);

    private final ProductsRepository productsRepository;

    private final ProductsMapper productsMapper;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductsMapper productsMapper) {
        this.productsRepository = productsRepository;
        this.productsMapper = productsMapper;
    }

    @Override
    public Mono<ProductsDTO> save(ProductsDTO productsDTO) {
        log.debug("Request to save Products : {}", productsDTO);
        return productsRepository.save(productsMapper.toEntity(productsDTO)).map(productsMapper::toDto);
    }

    @Override
    public Mono<ProductsDTO> update(ProductsDTO productsDTO) {
        log.debug("Request to save Products : {}", productsDTO);
        return productsRepository.save(productsMapper.toEntity(productsDTO).setIsPersisted()).map(productsMapper::toDto);
    }

    @Override
    public Mono<ProductsDTO> partialUpdate(ProductsDTO productsDTO) {
        log.debug("Request to partially update Products : {}", productsDTO);

        return productsRepository
            .findById(productsDTO.getId())
            .map(existingProducts -> {
                productsMapper.partialUpdate(existingProducts, productsDTO);

                return existingProducts;
            })
            .flatMap(productsRepository::save)
            .map(productsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ProductsDTO> findAll() {
        log.debug("Request to get all Products");
        return productsRepository.findAll().map(productsMapper::toDto);
    }

    public Mono<Long> countAll() {
        return productsRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ProductsDTO> findOne(String id) {
        log.debug("Request to get Products : {}", id);
        return productsRepository.findById(id).map(productsMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Products : {}", id);
        return productsRepository.deleteById(id);
    }
}
