package com.kinobooking.secure.dao.implementations;

import com.kinobooking.secure.dao.TicketDao;
import com.kinobooking.secure.entity.Booking;
import com.kinobooking.secure.entity.Seans;
import com.kinobooking.secure.entity.Seat;
import com.kinobooking.secure.entity.Ticket;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Екатерина on 03.09.2017.
 */
@Repository
public class TicketDaoImpl implements TicketDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Ticket createTicket(int seansId, int seatId, Booking book, Session session) {
        Ticket ticket =new Ticket();
        //Session session = sessionFactory.getCurrentSession();
        Query q1= session.createQuery(" from Seans where seansId="+ seansId);
        Seans seans=(Seans)q1.uniqueResult();
        ticket.setSeans(seans);
        ticket.setBooking(book);
        ticket.setCinema(seans.getCinema());
        ticket.setClient(book.getClient());
        ticket.setFilm(seans.getFilm());
        ticket.setHall(seans.getHall());
        Query q2= session.createQuery("from Seat where seatId="+seatId);
        ticket.setSeat((Seat)q2.uniqueResult());
        session.save(ticket);
        return ticket;
    }

    @Override
    public Set<Integer> getSeatsBySeansId(int seansId) {
        Set<Integer> seats=null;
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query= session.createQuery("select seat.seatId from Ticket  where seans=(from Seans where seansId="+ seansId+")");
        seats=new HashSet<>((List<Integer>)query.list());
        return seats;
    }
}
