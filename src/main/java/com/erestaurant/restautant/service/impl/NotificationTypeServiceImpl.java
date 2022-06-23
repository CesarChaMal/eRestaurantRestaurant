package com.erestaurant.restautant.service.impl;

import com.erestaurant.restautant.domain.NotificationType;
import com.erestaurant.restautant.repository.NotificationTypeRepository;
import com.erestaurant.restautant.service.NotificationTypeService;
import com.erestaurant.restautant.service.dto.NotificationTypeDTO;
import com.erestaurant.restautant.service.mapper.NotificationTypeMapper;
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
 * Service Implementation for managing {@link NotificationType}.
 */
@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private final Logger log = LoggerFactory.getLogger(NotificationTypeServiceImpl.class);

    private final NotificationTypeRepository notificationTypeRepository;

    private final NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(
        NotificationTypeRepository notificationTypeRepository,
        NotificationTypeMapper notificationTypeMapper
    ) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public Mono<NotificationTypeDTO> save(NotificationTypeDTO notificationTypeDTO) {
        log.debug("Request to save NotificationType : {}", notificationTypeDTO);
        return notificationTypeRepository.save(notificationTypeMapper.toEntity(notificationTypeDTO)).map(notificationTypeMapper::toDto);
    }

    @Override
    public Mono<NotificationTypeDTO> update(NotificationTypeDTO notificationTypeDTO) {
        log.debug("Request to save NotificationType : {}", notificationTypeDTO);
        return notificationTypeRepository
            .save(notificationTypeMapper.toEntity(notificationTypeDTO).setIsPersisted())
            .map(notificationTypeMapper::toDto);
    }

    @Override
    public Mono<NotificationTypeDTO> partialUpdate(NotificationTypeDTO notificationTypeDTO) {
        log.debug("Request to partially update NotificationType : {}", notificationTypeDTO);

        return notificationTypeRepository
            .findById(notificationTypeDTO.getId())
            .map(existingNotificationType -> {
                notificationTypeMapper.partialUpdate(existingNotificationType, notificationTypeDTO);

                return existingNotificationType;
            })
            .flatMap(notificationTypeRepository::save)
            .map(notificationTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<NotificationTypeDTO> findAll() {
        log.debug("Request to get all NotificationTypes");
        return notificationTypeRepository.findAll().map(notificationTypeMapper::toDto);
    }

    public Mono<Long> countAll() {
        return notificationTypeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<NotificationTypeDTO> findOne(String id) {
        log.debug("Request to get NotificationType : {}", id);
        return notificationTypeRepository.findById(id).map(notificationTypeMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete NotificationType : {}", id);
        return notificationTypeRepository.deleteById(id);
    }
}
