package com.kinobooking.secure.dao.implementations;

import com.kinobooking.secure.dao.CinemaDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;

/**
 * Created by Екатерина on 27.08.2017.
 */
@Repository
public class CinemaDaoImpl implements CinemaDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<String> getCinemaNames (){
        List<String> names= null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("select cinemaName from Cinema");
            names = (List<String>) query.list();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return names;

    }
}
