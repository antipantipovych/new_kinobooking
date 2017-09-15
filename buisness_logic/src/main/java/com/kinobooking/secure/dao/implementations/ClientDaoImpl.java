package com.kinobooking.secure.dao.implementations;

import com.kinobooking.secure.dao.ClientDao;
import com.kinobooking.secure.entity.Client;
import com.kinobooking.secure.exceptions.EmailExistsException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Locale;

/**
 * Created by Екатерина on 17.08.2017.
 */
@Repository
public class ClientDaoImpl implements ClientDao {
    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client getClient(String login){
        Client client= null;
        try {
            Locale.setDefault(Locale.ENGLISH);
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("from Client where email = '" + login+"'");
            client = (Client) query.uniqueResult();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void delete(Client client){
        try {
            Locale.setDefault(Locale.ENGLISH);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(client);
            session.flush();
            session.getTransaction().commit();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Client save(Client client){
         try {
            Locale.setDefault(Locale.ENGLISH);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(client);
            session.flush();
            session.getTransaction().commit();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean updateClient(Client client) {
        try {
            Locale.setDefault(Locale.ENGLISH);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(client);
            session.flush();
            session.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public Client registerNewClientAccount(Client account) throws EmailExistsException {

        if (emailExist(account.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            +  account.getEmail());
        }
        return save(account);

    }

    private boolean emailExist(String email) {
        Client client = getClient(email);
        if (client != null) {
            return true;
        }
        return false;
    }
}
