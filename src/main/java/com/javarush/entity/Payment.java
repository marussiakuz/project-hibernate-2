package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Builder
@Getter @Setter @ToString(exclude = {"staff", "rental", "amount", "paymentDate"})
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Short id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @OneToOne
    @Cascade(SAVE_UPDATE)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}
