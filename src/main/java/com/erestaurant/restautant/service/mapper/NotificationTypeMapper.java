package com.erestaurant.restautant.service.mapper;

import com.erestaurant.restautant.domain.NotificationType;
import com.erestaurant.restautant.service.dto.NotificationTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotificationType} and its DTO {@link NotificationTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotificationTypeMapper extends EntityMapper<NotificationTypeDTO, NotificationType> {}
