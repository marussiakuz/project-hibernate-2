package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;
import com.javarush.util.converter.RatingConverter;
import com.javarush.util.converter.SpecialFeaturesConverter;
import com.javarush.entity.enums.Rating;
import com.javarush.entity.enums.SpecialFeature;
import com.javarush.util.converter.YearConverter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@SuperBuilder
@Getter @Setter @ToString(exclude = "actors")
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(schema = "movie", name = "film")
public class Film extends FilmInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Convert(converter = YearConverter.class)
    @Column(name = "release_year", columnDefinition = "year")
    private Year releaseYear;

    @ManyToOne
    @Cascade(SAVE_UPDATE)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne
    @Cascade(SAVE_UPDATE)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false)
    private Byte rentalDuration;

    @Column(name = "rental_rate", nullable = false)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @ManyToMany(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(SAVE_UPDATE)
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actors;

    @Column(name = "replacement_cost", nullable = false)
    private BigDecimal replacementCost;

    @Convert(converter = RatingConverter.class)
    @Column(name = "rating", columnDefinition="enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private Rating rating;

    @Convert(converter = SpecialFeaturesConverter.class)
    @Column(name = "special_features",
            columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private Set<SpecialFeature> specialFeatures;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
}
