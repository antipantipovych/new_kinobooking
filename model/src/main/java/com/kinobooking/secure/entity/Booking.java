package com.kinobooking.secure.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Екатерина on 15.08.2017.
 */
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @Column(name="booking_id", unique = true, nullable = false)
    @GeneratedValue
    private int bookingId;
    @Column(name="booking_price")
    private int price;
    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;
    @OneToMany(mappedBy = "booking")
    private Set<Ticket> tickets;

    public Booking() {
    }

    public Booking(int bookingId, int price, Client client, Set<Ticket> tickets) {
        this.bookingId = bookingId;
        this.price = price;
        this.client = client;
        this.tickets = tickets;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
