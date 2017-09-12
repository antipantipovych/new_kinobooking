package com.kinobooking.secure.controller;

import com.kinobooking.secure.dto.BookingDto;
import com.kinobooking.secure.entity.Booking;
import com.kinobooking.secure.service.ClientDetailsServiceImpl;
import com.kinobooking.secure.service.TicketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        initBookingList(authentication.getName(), model);
        return "bookings";
    }

    public void initBookingList(String name, Model model){
        Set<Booking> bookingList = detailsService.getClient(name).getBookings();
        model.addAttribute("bookingList",bookingList);
    }

    @ModelAttribute("booking")
    public BookingDto initBookings(){
        return new BookingDto();
    }

    @RequestMapping( method = RequestMethod.POST)
    public String deleteBookings(@RequestParam("delete") int bookId, ModelMap model, Authentication authentication) {
        //System.out.println("!"+bookId);
        ticketDetailsService.deleteBook(bookId);
        Set<Booking> bookingList = detailsService.getClient(authentication.getName()).getBookings();
        model.addAttribute("bookingList",bookingList);
        return "bookings";
    }
}

