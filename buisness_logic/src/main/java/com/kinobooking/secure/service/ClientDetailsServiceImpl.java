package com.kinobooking.secure.service;

import com.kinobooking.secure.dao.ClientDao;
import com.kinobooking.secure.entity.Client;
import com.kinobooking.secure.entity.enums.UserRoleEnum;
import com.kinobooking.secure.exceptions.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Екатерина on 13.08.2017.
 */
@Service
public class ClientDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientDao.getClient(email);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(client.getEmail(),
                        client.getPassword(),roles);
        return userDetails;
    }

    public Client createUserAccount(Client account, BindingResult result) throws EmailExistsException {
        Client registered = null;
        registered = clientDao.registerNewClientAccount(account);
        return registered;
    }
}




