package com.javarush.entity.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public enum Rating {
    PG("PG"),
    G("G"),
    NC17("NC-17"),
    PG13("PG-13"),
    R("R");

    private final String designation;

    private static final Map<String, Rating> RATINGS;

    Rating(String designation) {
        this.designation = designation;
    }

    static {
        Map<String,Rating> ratingMap = new ConcurrentHashMap<>();

        Arrays.stream(Rating.values())
                .forEach(rating -> ratingMap.put(rating.designation, rating));

        RATINGS = Collections.unmodifiableMap(ratingMap);
    }

    public static Rating get(String value) {
        return RATINGS.get(value);
    }
}
