package com.kinobooking.secure.dao;

import com.kinobooking.secure.entity.Booking;
import com.kinobooking.secure.entity.Ticket;
import org.hibernate.Session;

import java.util.Set;

/**
 * Created by Екатерина on 03.09.2017.
 */
public interface TicketDao {
    public Set<Integer> getSeatsBySeansId(int seansId);

    public Ticket createTicket(int seansId, int seatId, Booking book,Session session);

}
