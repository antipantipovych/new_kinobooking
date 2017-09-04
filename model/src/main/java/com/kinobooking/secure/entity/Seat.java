package com.kinobooking.secure.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Екатерина on 14.08.2017.
 */
@Entity
@Table(name="seats")
public class Seat {
    @Id
    @Column(name="seat_id", unique = true,nullable=false)
    @GeneratedValue
    private int seatId;
    @Column(name="seat_row", nullable = false)
    private int seatRow;
    @Column(name="seat_num", nullable = false)
    private int seatNum;
    @Column(name="seat_avail", nullable = false)
    private int seatAvail;
    @ManyToOne
    @JoinColumn(name="hall_id",nullable = false)
    private Hall hall;
    @OneToMany(mappedBy = "seat")
    private Set<Ticket> tickets;

    public Seat() {
    }

    public Seat(int seatId, int seatRow, int seatNum, int seatAvail, Hall hall) {
        this.seatId = seatId;
        this.seatRow = seatRow;
        this.seatNum = seatNum;
        this.seatAvail = seatAvail;
        this.hall = hall;
    }

    public Seat(int seatId, int seatNum, int seatRow, int seatAvail, Hall hall, Set<Ticket> tickets) {
        this.seatId = seatId;
        this.seatNum = seatNum;
        this.seatRow = seatRow;
        this.seatAvail = seatAvail;
        this.hall = hall;
        this.tickets = tickets;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatAvail() {
        return seatAvail;
    }

    public void setSeatAvail(int seatAvail) {
        this.seatAvail = seatAvail;
    }


    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
