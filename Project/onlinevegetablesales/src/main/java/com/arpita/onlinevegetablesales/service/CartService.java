package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.CartDao;
import com.arpita.onlinevegetablesales.dto.CartDto;
import com.arpita.onlinevegetablesales.dto.CartItemDto;
import com.arpita.onlinevegetablesales.entity.Cart;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartDao cartDao;

    public void addVegetableToCart(User user, Vegetable vegetable,int quantity){
        Cart c = cartDao.findByUserAndVegetable(user, vegetable);
        if(c!=null){
            c.setQuantity(quantity);
            cartDao.save(c);
        }
        else{
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setVegetable(vegetable);
            cart.setQuantity(quantity);
            cartDao.save(cart);
        }
    }
    public CartDto listCartItems(User user){
        List<Cart> cartList = cartDao.findByUser(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for(Cart cart:cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0.0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getVegetable().getUnitPrice()*cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }
    public void deleteItem(Vegetable vegetable,User user){
        cartDao.deleteByUserAndVegetable(user, vegetable);
    }
    public void deleteAllItems(User user){
        cartDao.deleteByUser(user);
    }
}
