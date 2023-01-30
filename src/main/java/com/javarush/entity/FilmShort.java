package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter @ToString(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "film_text")
public class FilmShort extends FilmInfo {
    @Id
    @Column(name = "film_id")
    private Short id;
}
