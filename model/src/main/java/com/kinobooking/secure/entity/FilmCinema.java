package com.kinobooking.secure.entity;

import javax.persistence.*;

/**
 * Created by Екатерина on 15.08.2017.
 */
@Entity
@Table(name="film-cinema")
public class FilmCinema {
    @Id
    @Column(name="fc_id", unique = true, nullable = false)
    @GeneratedValue
    private int fcId;
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    @ManyToOne(targetEntity = Cinema.class)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Hall cinema;
}
