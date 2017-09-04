package com.kinobooking.secure.controller;

import com.kinobooking.secure.dto.ClientDto;
import com.kinobooking.secure.entity.Client;
import com.kinobooking.secure.service.ClientDetailsServiceImpl;
import com.kinobooking.secure.exceptions.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Екатерина on 15.08.2017.
 */
@Controller
@RequestMapping("/registr")
public class RegistrController {

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;
    @Autowired
    private ClientDetailsServiceImpl service;

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        return "registr";
    }

    @RequestMapping(method = RequestMethod.POST)

    public String submit(@Valid @ModelAttribute("client") ClientDto client,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "registr";
        }
        model.addAttribute("firstName", client.getFirstName());
        model.addAttribute("lastName", client.getLastName());
        model.addAttribute("email", client.getEmail());
        model.addAttribute("password", client.getPassword());
        model.addAttribute("confirmPass", client.getConfirmPass());
        Client registered = new Client();
        if (!result.hasErrors()) {
            try {
                Client c= new Client(shaPasswordEncoder.encodePassword(client.getPassword(),""),client.getLastName(),client.getEmail(),client.getFirstName());
                registered = service.createUserAccount(c, result);
            }
            catch (EmailExistsException e){
                result.rejectValue("email", "error.client", "An account already exists for this email");

                return "registr";
            }
        }
        if (registered == null) {
            return "registr";
        } else {
            return "redirect:/login";
        }
    }


    @ModelAttribute("client")
    public ClientDto newClient() {
        return new ClientDto();
    }
}
