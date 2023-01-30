package com.javarush.util.converter;

import jakarta.persistence.AttributeConverter;
import com.javarush.entity.enums.SpecialFeature;

import java.util.*;
import java.util.stream.Collectors;

public class SpecialFeaturesConverter implements AttributeConverter<Set<SpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(Set<SpecialFeature> attribute) {
        return attribute == null ? null : String.join(",",
                new ArrayList<>(attribute).stream()
                        .map(SpecialFeature::getValue)
                        .toList());
    }

    @Override
    public Set<SpecialFeature> convertToEntityAttribute(String dbData) {
        return dbData == null ? Collections.emptySet() : Arrays.stream(dbData.split(","))
                .map(SpecialFeature::get)
                .collect(Collectors.toSet());
    }
}
