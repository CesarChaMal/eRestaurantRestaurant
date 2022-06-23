package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.Notification;
import com.erestaurant.restautant.service.dto.NotificationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Notification} and its DTO {@link NotificationDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification> {}
