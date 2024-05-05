package com.arpita.onlinevegetablesales.controller;

import com.arpita.onlinevegetablesales.entity.Feedback;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import com.arpita.onlinevegetablesales.service.FeedbackService;
import com.arpita.onlinevegetablesales.service.UserService;
import com.arpita.onlinevegetablesales.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private VegetableService vegetableService;
    @Autowired
    private UserService userService;

    @GetMapping("/giveFeedback")
    public String feedbackForm(@RequestParam(value="param")String name, Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        Vegetable vegetable = vegetableService.getVegetableByName(name);
        theModel.addAttribute("vegetable",vegetable);
        Feedback feedback = feedbackService.getFeedback(user,vegetable);
        theModel.addAttribute("feed",feedback);
        theModel.addAttribute("user",user);
        return "feedbackForm";
    }
    @PostMapping("/postFeedback")
    public String postFeedback(@ModelAttribute("feed")Feedback feedback,@RequestParam(value="param")String name,Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        Vegetable vegetable = vegetableService.getVegetableByName(name);
        feedback.setUser(user);
        feedback.setVegetable(vegetable);
        feedbackService.addFeedback(feedback);
        theModel.addAttribute("user",user);
        return "thanksFeedback";
    }
}
