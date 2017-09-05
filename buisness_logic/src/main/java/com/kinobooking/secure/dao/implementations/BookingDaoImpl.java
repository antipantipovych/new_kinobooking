package com.kinobooking.secure.dao.implementations;

import com.kinobooking.secure.dao.BookingDao;
import com.kinobooking.secure.dao.ClientDao;
import com.kinobooking.secure.entity.Booking;
import com.kinobooking.secure.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Екатерина on 03.09.2017.
 */
@Repository
public class BookingDaoImpl implements BookingDao {
    @Autowired
    SessionFactory sessionFactory;



    @Autowired
    ClientDao clientDao;
    @Override
    public Booking createBook(Client client, Session session) {
        Booking book= new Booking();
        book.setClient(client);
        try {
           // Locale.setDefault(Locale.ENGLISH);
           // Session session = sessionFactory.getCurrentSession();
            session.save(book);
        }
        catch(Exception e){
            System.out.println("ERRRRRRRRRRRRRRRRRRROR");
            e.printStackTrace();
        }
        return book;
    }
}
