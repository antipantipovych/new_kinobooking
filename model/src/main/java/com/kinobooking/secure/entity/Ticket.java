package com.kinobooking.secure.entity;

import javax.persistence.*;

/**
 * Created by Екатерина on 15.08.2017.
 */
@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @Column(name="ticket_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_seq1")
    @SequenceGenerator(name="ticket_seq1", sequenceName="ticket_seq1", allocationSize=1)
    private int ticketId;
    @Column(name="ticket_price")
    private Integer price;
    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Seans getSeans() {
        return seans;
    }

    public void setSeans(Seans seans) {
        this.seans = seans;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @ManyToOne
    @JoinColumn(name="cinema_id", nullable = false)
    private Cinema cinema;
    @ManyToOne
    @JoinColumn(name="seans_id", nullable = false)
    private Seans seans;
    @ManyToOne
    @JoinColumn(name="hall_id", nullable = false)
    private Hall hall;
    @ManyToOne
    @JoinColumn(name="seat_id", nullable = false)
    private Seat seat;
    @ManyToOne
    @JoinColumn(name="film_id", nullable = false)
    private Film film;
    @ManyToOne
    @JoinColumn(name="booking_id", nullable = false)
    private Booking booking;

}
