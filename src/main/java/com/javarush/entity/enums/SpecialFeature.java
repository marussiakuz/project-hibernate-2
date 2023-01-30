package com.javarush.entity.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;
    private static final Map<String, SpecialFeature> SPECIAL_FEATURES;

    SpecialFeature(String value) {
        this.value = value;
    }

    static {
        Map<String,SpecialFeature> specialFeatureMap = new ConcurrentHashMap<>();

        Arrays.stream(SpecialFeature.values())
                .forEach(specialFeature -> specialFeatureMap.put(specialFeature.value, specialFeature));

        SPECIAL_FEATURES = Collections.unmodifiableMap(specialFeatureMap);
    }

    public static SpecialFeature get(String value) {
        return SPECIAL_FEATURES.get(value);
    }
}
