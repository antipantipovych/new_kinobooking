package com.kinobooking.secure.dao;

import com.kinobooking.secure.entity.Client;
import com.kinobooking.secure.exceptions.EmailExistsException;

/**
 * Created by Екатерина on 17.08.2017.
 */

public interface ClientDao {

    void delete(Client client);
    Client save (Client client) ;
    Client getClient(String login);
    Client registerNewClientAccount(Client client)throws EmailExistsException;
}
