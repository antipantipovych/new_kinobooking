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
    @GeneratedValue
    private int ticketId;
    @Column(name="ticket_price")
    private int price;
    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;
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
