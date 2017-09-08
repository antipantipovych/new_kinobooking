package com.kinobooking.secure.controller;

import com.kinobooking.secure.dto.ChangeClientDto;
import com.kinobooking.secure.entity.Client;
import com.kinobooking.secure.service.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Екатерина on 06.09.2017.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @RequestMapping( method = RequestMethod.GET)
    public String showAccount(Model model, Authentication authentication){
        ChangeClientDto clientDto= new ChangeClientDto();
        clientDto.setEmail(authentication.getName());
        Client client= clientDetailsService.getClient(authentication.getName());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        model.addAttribute("user",clientDto);
        return "account";
    }

    @RequestMapping(method = RequestMethod.POST, params="change")
    public String acceptChanges(@Valid @ModelAttribute("user") ChangeClientDto clientDto, BindingResult result, ModelMap model
                                ,Authentication authentication){
        clientDto.setEmail(authentication.getName());
        if(result.hasErrors()){
            return "account";
        }
        model.addAttribute("lastName",clientDto.getLastName());
        model.addAttribute("firstName",clientDto.getFirstName());
        model.addAttribute("newPassword", clientDto.getNewPassword());
        model.addAttribute("oldPassword", clientDto.getOldPassword());
        model.addAttribute("confirmPassword", clientDto.getConfirmPassword());
        System.out.println("! "+clientDto.getOldPassword());
        if(clientDto.getOldPassword()==null || clientDto.getOldPassword().equals("") ) {
            if (clientDetailsService.updateClient(clientDto.getLastName(), clientDto.getFirstName(), clientDto.getEmail())) {
                result.rejectValue("", "error.user", "Updates were saved");
            }
        }
        else{
            if(shaPasswordEncoder.encodePassword(clientDto.getOldPassword(),"").equals(clientDetailsService.getClient(authentication.getName()).getPassword())){
                if(clientDto.getNewPassword().length()>=6) {
                    if (clientDto.getNewPassword().equals(clientDto.getConfirmPassword())) {
                        if (clientDetailsService.updateClient(clientDto.getLastName(), clientDto.getFirstName(), clientDto.getEmail(),
                        shaPasswordEncoder.encodePassword(clientDto.getNewPassword(),""))){
                            result.rejectValue("", "error.user", "Updates were saved");
                        }
                    }
                    else result.rejectValue("confirmPassword", "error.user", "Passwords do not match");
                }
                else result.rejectValue("newPassword", "error.user", "Password should be 6 letters min");
            }
            else result.rejectValue("oldPassword", "error.user", "Not correct Password");
        }
        return "account";
    }

    @RequestMapping(method = RequestMethod.POST, params="delete")
    public String deleteAccount(ModelMap model,Authentication authentication){
        clientDetailsService.deleteClient(authentication.getName());
        return "redirect:/login?logout";
    }
}


