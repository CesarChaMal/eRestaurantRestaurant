package com.erestaurant.restautant.repository.rowmapper;

import com.erestaurant.restautant.domain.Restaurant;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Restaurant}, with proper type conversions.
 */
@Service
public class RestaurantRowMapper implements BiFunction<Row, String, Restaurant> {

    private final ColumnConverter converter;

    public RestaurantRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Restaurant} stored in the database.
     */
    @Override
    public Restaurant apply(Row row, String prefix) {
        Restaurant entity = new Restaurant();
        entity.setId(converter.fromRow(row, prefix + "_id", String.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setImageContentType(converter.fromRow(row, prefix + "_image_content_type", String.class));
        entity.setImage(converter.fromRow(row, prefix + "_image", byte[].class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setRating(converter.fromRow(row, prefix + "_rating", Float.class));
        return entity;
    }
}
