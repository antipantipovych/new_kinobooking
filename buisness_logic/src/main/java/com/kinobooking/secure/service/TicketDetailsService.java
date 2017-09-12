package com.kinobooking.secure.service;

import com.kinobooking.secure.dao.BookingDao;
import com.kinobooking.secure.dao.ClientDao;
import com.kinobooking.secure.dao.TicketDao;
import com.kinobooking.secure.entity.Booking;
import com.kinobooking.secure.entity.Client;
import com.kinobooking.secure.exceptions.NotAvailSeatException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Екатерина on 03.09.2017.
 */
@Service
public class TicketDetailsService {
    @Autowired
    TicketDao ticketDao;

    @Autowired
    BookingDao bookingDao;

    @Autowired
    ClientDao clientDao;

    @Autowired
    SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTickets(int seansId, List<Integer> seatsId,String userName) throws NotAvailSeatException{
        synchronized (this) {
            try {
                Set<Integer> booked = ticketDao.getSeatsBySeansId(seansId);
                for (Integer i : seatsId) {
                    if (booked.contains(i)) {
                        throw new NotAvailSeatException("Chosen seat is not available");
                    }
                }
                Client client =clientDao.getClient(userName);
                Locale.setDefault(Locale.ENGLISH);
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                Booking book= bookingDao.createBook(client, session);
                for(Integer i:seatsId){
                    ticketDao.createTicket( seansId, i,book,session );
                }
                session.flush();
                session.getTransaction().commit();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    public void deleteBook(int bookId){
        bookingDao.delete(bookId);
    }
}

