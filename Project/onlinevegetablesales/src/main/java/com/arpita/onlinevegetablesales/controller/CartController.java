package com.arpita.onlinevegetablesales.controller;

import com.arpita.onlinevegetablesales.dto.CartDto;
import com.arpita.onlinevegetablesales.dto.CartItemDto;
import com.arpita.onlinevegetablesales.entity.Address;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import com.arpita.onlinevegetablesales.service.AddressService;
import com.arpita.onlinevegetablesales.service.CartService;
import com.arpita.onlinevegetablesales.service.UserService;
import com.arpita.onlinevegetablesales.service.VegetableService;
import com.arpita.onlinevegetablesales.user.WebAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private VegetableService vegetableService;
    @Autowired
    private AddressService addressService;


    @GetMapping("/")
    public String showCart(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        CartDto cartDto = cartService.listCartItems(user);
        theModel.addAttribute("cartDto",cartDto);
        theModel.addAttribute("user",user);
        return "myCart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam(value="param1") String veg, @RequestParam(value="param2")String unit, Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        Vegetable vegetable = vegetableService.getVegetableByName(veg);
        int quantity = Integer.parseInt(unit);
        cartService.addVegetableToCart(user,vegetable,quantity);
        CartDto cartDto = cartService.listCartItems(user);
        theModel.addAttribute("cartDto",cartDto);
        theModel.addAttribute("user",user);
        return "myCart";
    }
    @PostMapping("/delete")
    public String updateItem(@RequestParam(value="param1")String name, Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        Vegetable vegetable = vegetableService.getVegetableByName(name);
        cartService.deleteItem(vegetable,user);
        CartDto newCartDto = cartService.listCartItems(user);
        theModel.addAttribute("cartDto",newCartDto);
        theModel.addAttribute("user",user);
        return "myCart";
    }
    @PostMapping("/clear")
    public String clearCart(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        cartService.deleteAllItems(user);
        theModel.addAttribute("user",user);
        return "myCart";
    }

    @GetMapping("/checkout")
    public String checkOut(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        CartDto cartDto = cartService.listCartItems(user);
        theModel.addAttribute("cartDto",cartDto);
        theModel.addAttribute("webAddress",new WebAddress());
        theModel.addAttribute("user",user);
        return "checkout";
    }
}
