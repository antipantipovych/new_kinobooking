package com.kinobooking.secure.controller;

import com.kinobooking.secure.dto.SeansDto;
import com.kinobooking.secure.entity.Seat;
import com.kinobooking.secure.exceptions.NotAvailSeatException;
import com.kinobooking.secure.service.SeansDetailsService;
import com.kinobooking.secure.service.TicketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by Екатерина on 03.09.2017.
 */
@Controller
@RequestMapping("/choose/schema")
public class SchemaController {
    @Autowired
    private SeansDetailsService seansDetailsService;
    @Autowired
    private TicketDetailsService ticketDetailsService;

    @RequestMapping( method = RequestMethod.GET)
    public String showSchema(@ModelAttribute("seans") SeansDto seans,Model model){
        initSeatSet(seans,model);
        System.out.println("here");
        return "choose/schema";
    }


    @RequestMapping(method = RequestMethod.POST )
    public String findBook(@Valid @ModelAttribute("seans") SeansDto seans, BindingResult result, ModelMap model, Authentication authentication){
        model.addAttribute("seatsForBook", seans.getSeatsForBook());
        if(seans.getSeatsForBook().size()==0) {
            result.rejectValue("seatsForBook", "error.seans", "Seats should be choosed");
            return "redirect:/choose/schema";
        }
        System.out.println(authentication.getName());
        try {
            ticketDetailsService.createTickets(seans.getSeansId(), seans.getSeatsForBook(), authentication.getName());
        }
        catch(NotAvailSeatException e){
            result.rejectValue("seatsForBook", "error.seans", "Seats are not availed");
            return "redirect:/choose/schema";
        }
      return "redirect:/choose/schema";
    }

    public void initSeatSet(SeansDto seansDto,Model model ){
        Set<Integer> rows=null;
        rows=seansDetailsService.getHallRows(seansDto.getSeansId());
        System.out.println("hello");
        for(Integer i: rows){
            System.out.println(i);
        }
        model.addAttribute("rows", rows);
        List<Seat> seats= seansDetailsService.getSeats(seansDto.getSeansId());
        model.addAttribute("seats", seats);
        Set<Integer> blockedSeats = seansDetailsService.getBlockedSeats(seansDto.getSeansId());
        model.addAttribute("blockedSeats", blockedSeats);
    }
}
