package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "staff")
public class Staff extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte id;

    @Lob
    @Column(name = "picture", columnDefinition="BLOB")
    private byte[] picture;

    @Column(length = 16, nullable = false)
    private String username;

    @Column(length = 40)
    private String password;
}
