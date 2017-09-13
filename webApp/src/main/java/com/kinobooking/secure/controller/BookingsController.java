package com.kinobooking.secure.controller;

import com.kinobooking.secure.entity.Booking;
import com.kinobooking.secure.service.ClientDetailsServiceImpl;
import com.kinobooking.secure.service.TicketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * Created by Екатерина on 11.09.2017.
 */
@Controller
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    ClientDetailsServiceImpl detailsService;

    @Autowired
    TicketDetailsService ticketDetailsService;
    @RequestMapping( method = RequestMethod.GET)
    public String showBookings(Model model, Authentication authentication) {
        model.addAttribute("bookingList",initBookingList(authentication.getName()));
        return "bookings";
    }

    public Set<Booking> initBookingList(String name){
        return detailsService.getClient(name).getBookings();

    }


    @RequestMapping( method = RequestMethod.POST)
    public String deleteBookings(@RequestParam("delete") int bookId, ModelMap model, Authentication authentication) {
        ticketDetailsService.deleteBook(bookId);
        Set<Booking> bookingList = detailsService.getClient(authentication.getName()).getBookings();
        model.addAttribute("bookingList",initBookingList(authentication.getName()));
        return "bookings";
    }
}

