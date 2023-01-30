package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@SuperBuilder
@Getter @Setter @ToString(exclude = {"store", "email", "address", "active"})
@AllArgsConstructor @NoArgsConstructor
@MappedSuperclass
public abstract class User extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne()
    @Cascade(SAVE_UPDATE)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "active", columnDefinition = "BIT", nullable = false)
    private Boolean active;
}
