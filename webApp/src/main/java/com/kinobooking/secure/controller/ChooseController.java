package com.kinobooking.secure.controller;

import com.kinobooking.secure.dto.SeansDto;
import com.kinobooking.secure.entity.Seans;
import com.kinobooking.secure.service.SeansDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by Екатерина on 24.08.2017.
 */
@Controller
@RequestMapping("/choose")
public class ChooseController {
    @Autowired
    private SeansDetailsService seansDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    public String showChooseForm(Model model){
        return "choose";
    }


    @RequestMapping(method = RequestMethod.POST, params="book" )
    public String findBook(HttpServletRequest request, @Valid @ModelAttribute("seans") SeansDto seans, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return "choose";
        }
        model.addAttribute("seansId", seans.getSeansId());
        request.getSession().setAttribute("seansDto",seans);
        return "redirect:/choose/schema";
    }

    @RequestMapping(method = RequestMethod.POST , params="find")
    public String submit(@ModelAttribute("seans") SeansDto seans , @RequestParam(value = "seansDate", required = false)Date date,
                         BindingResult result, ModelMap model){
        //model.put("seansDate",date);
        model.addAttribute("filmName", seans.getFilmName());
        model.addAttribute("cinemaName", seans.getCinemaName());
        if(!date.before(new Date())){seans.setSeansDate(date);}
        model.addAttribute("threeD", seans.isThreeD());
        List<Seans> list =seansDetailsService.findSeanses(seans.getFilmName(),seans.getCinemaName(),seans.getSeansDate(),seans.isThreeD());
        model.addAttribute("shortSeansNames",initShortSeansNames(list));
        model.addAttribute("listOfSeanses", list);
        return "choose";
    }

    @ModelAttribute(value="seans")
    public SeansDto newSeans() {
        return new SeansDto();
    }

    @ModelAttribute(value="listOfFilmNames")
    public List<String> initFilmNameList(){
        return seansDetailsService.loadFilmNames();

    }

    @ModelAttribute(value="listOfCinemaNames")
    public List<String> initCinemaNameList(){
        return seansDetailsService.loadCinemaNames();

    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
    }

    public Set<String> initShortSeansNames(List<Seans> seanses){
        Set<String> set= new TreeSet<>();
        for(Seans c: seanses){
            set.add(c.toShortString());

        }
        return set;
    }
}
