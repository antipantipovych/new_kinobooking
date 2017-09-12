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
        model.addAttribute("rows", initRows(seans));
        model.addAttribute("seats",initSeats(seans));
        model.addAttribute("blockedSeats",initBlockedSeats(seans));
        return "choose/schema";
    }


    @RequestMapping(method = RequestMethod.POST )
    public String findBook(@Valid @ModelAttribute("seans") SeansDto seans, BindingResult result, ModelMap model, Authentication authentication){
        model.addAttribute("seatsForBook", seans.getSeatsForBook());
        model.addAttribute("seansId",seans.getSeansId());
        model.addAttribute("rows", initRows(seans));
        model.addAttribute("seats",initSeats(seans));
        model.addAttribute("blockedSeats",initBlockedSeats(seans));
        if(seans.getSeatsForBook()==null) {
            result.rejectValue("", "error.seans", "Seats should be choosed");
            return "choose/schema";
        }
        System.out.println(authentication.getName());
        try {
            ticketDetailsService.createTickets(seans.getSeansId(), seans.getSeatsForBook(), authentication.getName());
        }
        catch(NotAvailSeatException e){
            result.rejectValue("", "error.seans", "Ooops, somebody was faster..");
            seans.setSeatsForBook(null);
            model.addAttribute("seatsForBook",seans.getSeatsForBook());
            return "choose/schema";
        }
      return "redirect:/bookings";
    }


    public Set<Integer> initRows(SeansDto seansDto){
        Set<Integer> rows=null;
        rows=seansDetailsService.getHallRows(seansDto.getSeansId());
        return rows;
    }

    public List<Seat> initSeats(SeansDto seansDto){
        List<Seat> seats=null;
        seats= seansDetailsService.getSeats(seansDto.getSeansId());
        return seats;
    }

    public Set<Integer> initBlockedSeats(SeansDto seansDto){
        Set<Integer> blockedSeats= null;
        blockedSeats = seansDetailsService.getBlockedSeats(seansDto.getSeansId());
        return blockedSeats;
    }
}
