package com.javarush.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

@SuperBuilder
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@MappedSuperclass
public abstract class FilmInfo {
    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Type(type="org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;
}
