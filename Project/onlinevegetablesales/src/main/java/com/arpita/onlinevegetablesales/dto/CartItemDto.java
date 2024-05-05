package com.arpita.onlinevegetablesales.dto;

import com.arpita.onlinevegetablesales.entity.Cart;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private int quantity;
    private Vegetable vegetable;
    public CartItemDto(Cart cart){
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.vegetable = cart.getVegetable();
    }
}
