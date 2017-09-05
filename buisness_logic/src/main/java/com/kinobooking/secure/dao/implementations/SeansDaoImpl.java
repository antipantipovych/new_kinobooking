package com.kinobooking.secure.dao.implementations;

import com.kinobooking.secure.dao.SeansDao;
import com.kinobooking.secure.entity.Seans;
import com.kinobooking.secure.entity.Seat;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

/**
 * Created by Екатерина on 24.08.2017.
 */
@Repository
public class SeansDaoImpl implements SeansDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Seat> getSeatsBySeansId(int seansId) {
        List<Seat> seats= null;
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seat where hall = (select hall from Seans where seansId="+ seansId+
                ") order by seatRow");
        System.out.println(query);
        seats =((List<Seat>)query.list());
        session.close();
        return seats;
    }

    @Override
    public TreeSet<Integer> getHallRowBySeabsId(int seansId) {
        TreeSet<Integer> rows= null;
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select seatRow from Seat where hall = (select hall from Seans where seansId="+ seansId+
                ") order by seatRow");
        System.out.println(query);
        rows = new TreeSet((List<Integer>)query.list());
        session.close();
        return rows;
    }

    @Override
    @Transactional
    public List<Seans> findByThreeD(int i) {
        List<Seans> list= null;
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and seansDate >= (select sysdate from dual) order by seansDate, cinema_id, film_id ");
        System.out.println(query);
        list = (List<Seans>)query.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Seans> findByDate(Date date, int i) {
        List<Seans> list= null;
        SimpleDateFormat newFormat= new SimpleDateFormat("MM.dd.yyyy");
        String newDate= newFormat.format(date);
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and seansDate = to_date('"+newDate+"', 'MM.dd.yyyy') order by  seansDate, cinema_id, film_id ");
        list = (List<Seans>) query.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Seans> findByCinemaThreeD(String cinemaName, int i) {
        List<Seans> list= null;
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and cinema_Id= " +
                "(select cinemaId from Cinema where cinemaName='"+cinemaName+"')and seansDate >= (select sysdate from dual) order by  seansDate, cinema_id, film_id ");
        list = (List<Seans>) query.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Seans> findByCinemaDate(String cinemaName, Date date, int i) {
        List<Seans> list= null;
        Locale.setDefault(Locale.ENGLISH);
        SimpleDateFormat newFormat= new SimpleDateFormat("MM.dd.yyyy");
        String newDate= newFormat.format(date);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and seansDate = to_date('"+newDate+"', 'MM.dd.yyyy') and cinema_Id= " +
                "(select cinemaId from Cinema where cinemaName='"+cinemaName+"') order by  seansDate, cinema_id, film_id ");
        list = (List<Seans>) query.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Seans> findByFilmThreeD(String filmName, int i) {
        List<Seans> list= null;
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and film_Id= " +
                "(select filmId from Film where filmName='"+filmName+"')and seansDate >= (select sysdate from dual) order by  seansDate, cinema_id, film_id ");
        list = (List<Seans>) query.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Seans> findByFilmDate(String filmName, Date date, int i) {
        List<Seans> list= null;
        SimpleDateFormat newFormat= new SimpleDateFormat("MM.dd.yyyy");
        String newDate= newFormat.format(date);
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and seansDate = to_date('"+newDate+"', 'MM.dd.yyyy') and film_Id= " +
                "(select filmId from Film where filmName='"+filmName+"') order by  seansDate, cinema_id, film_id ");
        list = (List<Seans>) query.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Seans> findByFilmCinemaThreeD(String filmName, String cinemaName, int i) {
        List<Seans> list= null;
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and cinema_Id= " +
                "(select cinemaId from Cinema where cinemaName='"+cinemaName+"') and film_Id= " +
                "(select filmId from Film where filmName='"+filmName+"')and seansDate >= (select sysdate from dual) order by  seansDate, cinema_id, film_id ");
        list = (List<Seans>) query.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Seans> findByFilmCinemaDate(String filmName, String cinemaName, Date date, int i) {
        List<Seans> list= null;
        SimpleDateFormat newFormat= new SimpleDateFormat("MM.dd.yyyy");
        String newDate= newFormat.format(date);
        Locale.setDefault(Locale.ENGLISH);
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Seans where seans_3D = " + i+" and seansDate = to_date('"+newDate+"', 'MM.dd.yyyy') and cinema_Id= " +
                "(select cinemaId from Cinema where cinemaName='"+cinemaName+"') and film_Id= " +
                "(select filmId from Film where filmName='"+filmName+"')and seansDate >= (select sysdate from dual) order by  seansDate, cinema_id, film_id ");
        list = (List<Seans>) query.list();
        session.close();
        return list;
    }
}
