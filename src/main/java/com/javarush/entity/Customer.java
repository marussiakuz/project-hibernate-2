package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@SuperBuilder
@Getter @Setter @ToString(callSuper = true, exclude = {"createDate"})
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "customer")
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Short id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;
}
