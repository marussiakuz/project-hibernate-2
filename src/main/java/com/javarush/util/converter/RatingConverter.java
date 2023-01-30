package com.javarush.util.converter;

import jakarta.persistence.AttributeConverter;
import com.javarush.entity.enums.Rating;

public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute == null ? null : attribute.getDesignation();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Rating.get(dbData);
    }
}
