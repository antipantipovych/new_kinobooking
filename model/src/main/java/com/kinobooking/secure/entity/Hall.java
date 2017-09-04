package com.kinobooking.secure.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Екатерина on 14.08.2017.
 */
@Entity
@Table(name="hall")
public class Hall {
    @Id
    @Column(name="hall_id", unique = true, nullable = false)
    @GeneratedValue
    private int hallId;
    @Column(name="hall_num", nullable = false)
    private int hallNum;
    @ManyToOne(targetEntity = Cinema.class)
    @JoinColumn(name="cinema_id",nullable = false)
    private Cinema cinema;
    @OneToMany(mappedBy = "hall")
    private List<Seat> seats;
    @OneToMany(mappedBy = "hall")
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "hall")
    private List<Seans> seanses;
    @OneToMany(mappedBy = "hall")
    private List<FilmHall> FHTables;

    public Hall() {
    }

    public Hall(int hallId, int hallNum, Cinema cinema, List<Seat> seats) {
        this.hallId = hallId;
        this.hallNum = hallNum;
        this.cinema = cinema;
        this.seats = seats;
    }

    public Hall(int hallId, int hallNum, Cinema cinema, List<Seat> seats, List<Ticket> tickets, List<FilmHall> FHTables, List<Seans> seanses) {
        this.hallId = hallId;
        this.hallNum = hallNum;
        this.cinema = cinema;
        this.seats = seats;
        this.tickets = tickets;
        this.FHTables = FHTables;
        this.seanses = seanses;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(List<Seans> seanses) {
        this.seanses = seanses;
    }

    public List<FilmHall> getFHTables() {
        return FHTables;
    }

    public void setFHTables(List<FilmHall> FHTables) {
        this.FHTables = FHTables;
    }

    public int getHallNum() {
        return hallNum;
    }

    public void setHallNum(int hallNum) {
        this.hallNum = hallNum;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
