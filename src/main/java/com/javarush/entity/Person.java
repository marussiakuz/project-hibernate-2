package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@SuperBuilder
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@MappedSuperclass
public abstract class Person {
    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}
