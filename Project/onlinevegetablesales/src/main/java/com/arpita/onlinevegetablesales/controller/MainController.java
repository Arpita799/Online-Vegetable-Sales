package com.arpita.onlinevegetablesales.controller;

import com.arpita.onlinevegetablesales.entity.Category;
import com.arpita.onlinevegetablesales.entity.Feedback;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import com.arpita.onlinevegetablesales.service.CategoryService;
import com.arpita.onlinevegetablesales.service.FeedbackService;
import com.arpita.onlinevegetablesales.service.UserService;
import com.arpita.onlinevegetablesales.service.VegetableService;
import com.arpita.onlinevegetablesales.user.UpdateProfile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private List<Category> categories;

    private List<Vegetable> vegetables;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VegetableService vegetableService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;


    @GetMapping("/home")
    public String Home(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        categories = categoryService.getAllCategory();
        theModel.addAttribute("categories",categories);
        theModel.addAttribute("user",user);
        return "home";
    }

    @GetMapping("/")
    public String showHomePage(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        categories = categoryService.getAllCategory();
        theModel.addAttribute("categories",categories);
        theModel.addAttribute("user",user);
        return "home";
    }

    @GetMapping("/category")
    public String getVegetablesByCategory(@RequestParam(value="param1")String  name, Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        Category category = categoryService.getCategoryByName(name);
        vegetables = vegetableService.getVegetableByCategory(category);
        theModel.addAttribute("category",category);
        theModel.addAttribute("vegetables",vegetables);
        theModel.addAttribute("user",user);
        return "vegetablesByCategory";
    }
    @GetMapping("/vegetable")
    public String getVegetable(@RequestParam(value="param1") String name, Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        name = name.toLowerCase();
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        Vegetable theVegetable = vegetableService.getVegetableByName(name);
        List<Feedback> feedbacks = feedbackService.getFeedbackByVegetable(theVegetable);
        theModel.addAttribute("feedbacks",feedbacks);
        theModel.addAttribute("vegetable",theVegetable);
        theModel.addAttribute("user",user);
        return "vegetable";
    }
    @GetMapping("/myAccount")
    public String updateProfile(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        UpdateProfile userProfile = new UpdateProfile(user.getFirstName(),user.getLastName(),user.getEmail());
        theModel.addAttribute("userProfile",userProfile);
        theModel.addAttribute("user",user);
        return "profileUpdateForm";
    }
    @PostMapping("/deleteProfile")
    public String deleteProfile(HttpServletRequest request) throws Exception{
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        request.logout();
        userService.deleteUser(user);
        return "login";
    }
    @PostMapping("updateProfile")
    public String updateProfile(@Valid @ModelAttribute("updateProfile")UpdateProfile updateProfile, BindingResult result, HttpSession session,Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        if(result.hasErrors()){
            UpdateProfile userProfile = new UpdateProfile();
            userProfile.setFirstName(user.getFirstName());
            userProfile.setLastName(user.getLastName());
            userProfile.setEmail(user.getEmail());
            theModel.addAttribute("userProfile",userProfile);
            theModel.addAttribute("user",user);
            return "profileUpdateForm";
        }
        userService.updateUser(user.getId(),updateProfile);
        theModel.addAttribute("user",user);
        return "profileUpdated";
    }
}
