package com.kinobooking.secure.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Екатерина on 13.08.2017.
 */
@Entity
@Table(name="Client")
public class Client {
    @Id
    @Column(name="client_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="client_seq")
    @SequenceGenerator(name="client_seq", sequenceName="client_seq", allocationSize=1)
    private int clientId;

    @Column(name="password", nullable = false, length = 50)
    private String password;

    @Column(name="first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name="last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name="email", nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "client")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy="client")
    private Set<Booking> bookings;

    public Client(String password, String lastName, String email, String firstName) {
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
    }

    public Client(int clientId, String password, String firstName, String lastName, String email, Set<Ticket> tickets, Set<Booking> bookings) {
        this.clientId = clientId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tickets = tickets;
        this.bookings = bookings;
    }

    public Client(int clientId,  String password, String firstName, String lastName, String email) {
        this.clientId = clientId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Client() {

    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
