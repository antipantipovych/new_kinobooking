package com.kinobooking.secure.entity;


import javax.persistence.*;

/**
 * Created by Екатерина on 15.08.2017.
 */
@Entity
@Table(name="film-hall")
public class FilmHall {
    @Id
    @Column(name="fh_id", unique = true, nullable = false)
    @GeneratedValue
    private int fhId;
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    public FilmHall() {
    }

    public FilmHall(int fhId, Film film, Hall hall) {
        this.fhId = fhId;
        this.film = film;
        this.hall = hall;
    }

    public FilmHall(int fhId) {
        this.fhId = fhId;

    }

    public int getFhId() {
        return fhId;
    }

    public void setFhId(int fhId) {
        this.fhId = fhId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
