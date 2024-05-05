package com.arpita.onlinevegetablesales.controller;

import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.service.UserService;
import com.arpita.onlinevegetablesales.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    //private Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private UserService userService;
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel) {

        theModel.addAttribute("webUser", new WebUser());

        return "registration-form";
    }
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser theWebUser,
            BindingResult theBindingResult,
            HttpSession session, Model theModel
    ){
        String username = theWebUser.getUserName();
        User existing = userService.getUserByUserName(username);
        if(existing!=null){
            theModel.addAttribute("webUser",new WebUser());
            theModel.addAttribute("registrationError","User name already exists");
            return "registration-form";
        }
        if(theBindingResult.hasErrors())
            return "registration-form";
        userService.save(theWebUser);
        session.setAttribute("user",theWebUser);
        return "registration-confirmation";
    }
}
