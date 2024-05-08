package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.AddressDao;
import com.arpita.onlinevegetablesales.dao.OrderDao;
import com.arpita.onlinevegetablesales.dto.CartDto;
import com.arpita.onlinevegetablesales.dto.CartItemDto;
import com.arpita.onlinevegetablesales.entity.Address;
import com.arpita.onlinevegetablesales.entity.Order;
import com.arpita.onlinevegetablesales.entity.OrderItem;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.user.WebAddress;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private CartService cartService;
    public void newOrder(User user, CartDto cartDto, Address address){
        Order order = new Order();
        order.setUser(user);
        order.setBillingAddress(address);
        order.setShippingAddress(address);
        order.setStatus("Placed");
        int totalQuantity = 0;
        for(CartItemDto cartItem : cartDto.getCartItems()){
            totalQuantity += cartItem.getQuantity();
        }
        order.setTotalQuantity(totalQuantity);
        if(cartDto.getTotalCost()>999){
            order.setTotalPrice(cartDto.getTotalCost());
            order.setDeliveryCharge(0);
        }
        else{
            order.setTotalPrice(cartDto.getTotalCost()+60);
            order.setDeliveryCharge(60);
        }
        orderDao.save(order);
        addressDao.save(address);
        for(CartItemDto cartItem : cartDto.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setVegetable(cartItem.getVegetable());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getVegetable().getUnitPrice());
            orderItem.setImageUrl(cartItem.getVegetable().getImageUrl());
            orderItem.setOrder(order);
            orderItemService.addOrderItem(orderItem);
        }
        cartService.deleteAllItems(user);
    }
    public List<Order> getAllOrders(User user){
        return orderDao.findOrderByUser(user);
    }
    public Order getOrderById(int id){
        return orderDao.findById(id);
    }
}
