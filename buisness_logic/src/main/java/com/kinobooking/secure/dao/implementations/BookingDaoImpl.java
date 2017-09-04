package com.kinobooking.secure.dao.implementations;

import com.kinobooking.secure.dao.BookingDao;
import com.kinobooking.secure.dao.ClientDao;
import com.kinobooking.secure.entity.Booking;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Locale;

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
    public Booking createBook(String userName) {
        Booking book= new Booking();
        book.setClient(clientDao.getClient(userName));
        try {
            Locale.setDefault(Locale.ENGLISH);
            Session session = sessionFactory.getCurrentSession();
            session.save(book);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return book;
    }
}
