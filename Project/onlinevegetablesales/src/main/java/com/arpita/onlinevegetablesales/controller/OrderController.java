package com.arpita.onlinevegetablesales.controller;

import com.arpita.onlinevegetablesales.dto.CartDto;
import com.arpita.onlinevegetablesales.entity.Address;
import com.arpita.onlinevegetablesales.entity.Order;
import com.arpita.onlinevegetablesales.entity.OrderItem;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.service.*;
import com.arpita.onlinevegetablesales.user.WebAddress;
import com.arpita.onlinevegetablesales.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/placeOrder")
    public String orderConfirmation(@Valid @ModelAttribute("webAddress") WebAddress theWebAddress,
                                    BindingResult theBindingResult, Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        CartDto cartDto = cartService.listCartItems(user);
        theModel.addAttribute("cartDto",cartDto);
        if(theBindingResult.hasErrors())
            return "cart/checkout";
        Address address = addressService.addAddress(theWebAddress);
        orderService.newOrder(user,cartDto,address);
        theModel.addAttribute("user",user);
        return "confirmedOrder";
    }
    @GetMapping("/showOrders")
    public String showMyOrders(Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        List<Order> orders = orderService.getAllOrders(user);
        theModel.addAttribute("orders",orders);
        theModel.addAttribute("user",user);
        return "myOrders";
    }
    @GetMapping("/orderDetails")
    public String showOrderDetails(@RequestParam(value="param")int id, Model theModel){
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(currentUserName);
        Order order = orderService.getOrderById(id);
        List<OrderItem> orderItems = orderItemService.getAllOrderItems(order);
        theModel.addAttribute("order",order);
        theModel.addAttribute("orderItems",orderItems);
        theModel.addAttribute("user",user);
        return "myOrderDetails";
    }
}
