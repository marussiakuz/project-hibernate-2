package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Getter @Setter @ToString(callSuper = true)
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "actor")
public class Actor extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    @ToString.Exclude
    private Set<Film> films;
}
