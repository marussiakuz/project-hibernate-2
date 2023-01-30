package com.javarush.entity;

import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Short id;

    @Column(name = "address", length = 50, nullable = false)
    private String line;

    @Column(name = "address2", length = 50)
    private String lineExtra;

    @Column(name = "district", length = 20, nullable = false)
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(length = 20, nullable = false)
    private String phone;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}
