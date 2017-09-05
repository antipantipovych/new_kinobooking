package com.kinobooking.secure.dao;

import com.kinobooking.secure.entity.Booking;
import com.kinobooking.secure.entity.Client;
import org.hibernate.Session;

/**
 * Created by Екатерина on 03.09.2017.
 */
public interface BookingDao {
    public Booking createBook(Client client, Session session);
}
