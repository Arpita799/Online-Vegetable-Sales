package com.arpita.onlinevegetablesales.controller;

import com.arpita.onlinevegetablesales.entity.ContactUs;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.service.ContactUsService;
import com.arpita.onlinevegetablesales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contactUs")
public class ContactUsController {
    @Autowired
    private ContactUsService contactUsService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String contactUs(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        ContactUs contactUs = contactUsService.getContactDetails(user);
        theModel.addAttribute("contactUs",contactUs);
        theModel.addAttribute("user",user);
        return "contactUs-form";
    }
    @PostMapping("/submitMessage")
    public String submitMessage(@ModelAttribute("contactUs")ContactUs contactUs,Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        contactUs.setUser(user);
        contactUsService.raiseQuery(contactUs);
        theModel.addAttribute("user",user);
        return "messageSent";
    }
}
