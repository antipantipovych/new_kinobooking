package com.kinobooking.secure.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Екатерина on 14.08.2017.
 */

@Entity
@Table(name="film")
public class Film {
    @Id
    @Column(name="film_id",unique = true, nullable = false)
    @GeneratedValue
    private int filmId;
    @Column(name="film_name", nullable = false,length = 30)
    private String filmName;
    @Column(name="film_descrip", length=700)
    private String filmDescrip;
    @Column(name="film_duration")
    private Date filmDuration;
    @Column(name="film_price", nullable = false)
    private int filmPrice;

    @OneToMany(mappedBy = "film")
    private Set<Ticket> tickets;
    @OneToMany(mappedBy = "film")
    private Set<FilmCinema> FCTables;
    @OneToMany(mappedBy = "film")
    private Set<FilmHall> FHTables;
    @OneToMany(mappedBy = "film")
    private Set<Seans> seanses;

    public Film() {
    }

    public Film(int filmId, String filmDescrip, String filmName, int filmPrice, Date filmDuration) {
        this.filmId = filmId;
        this.filmDescrip = filmDescrip;
        this.filmName = filmName;
        this.filmPrice = filmPrice;
        this.filmDuration = filmDuration;
    }

    public Film(int filmId, String filmName, String filmDescrip, Date filmDuration, int filmPrice, Set<Ticket> tickets, Set<FilmCinema> FCTables, Set<FilmHall> FHTables, Set<Seans> seanses) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmDescrip = filmDescrip;
        this.filmDuration = filmDuration;
        this.filmPrice = filmPrice;
        this.tickets = tickets;
        this.FCTables = FCTables;
        this.FHTables = FHTables;
        this.seanses = seanses;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<FilmCinema> getFCTables() {
        return FCTables;
    }

    public void setFCTables(Set<FilmCinema> FCTables) {
        this.FCTables = FCTables;
    }

    public Set<FilmHall> getFHTables() {
        return FHTables;
    }

    public void setFHTables(Set<FilmHall> FHTables) {
        this.FHTables = FHTables;
    }

    public Set<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(Set<Seans> seanses) {
        this.seanses = seanses;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }


    public String getFilmDescrip() {
        return filmDescrip;
    }

    public void setFilmDescrip(String filmDescrip) {
        this.filmDescrip = filmDescrip;
    }


    public Date getFilmDuration() {
        return filmDuration;
    }

    public void setFilmDuration(Date filmDuration) {
        this.filmDuration = filmDuration;
    }

    public int getFilmPrice() {
        return filmPrice;
    }

    public void setFilmPrice(int filmPrice) {
        this.filmPrice = filmPrice;
    }
}
