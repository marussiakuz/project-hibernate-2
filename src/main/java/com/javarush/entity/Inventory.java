package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(SAVE_UPDATE)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", film id=" + film.getId() +
                ", store id=" + store.getId() +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
