package com.arpita.onlinevegetablesales.dto;

import lombok.Data;


import java.util.List;
@Data
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;

    public CartDto(List<CartItemDto> cartItems, double totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }
    public CartDto(List<CartItemDto> cartItems){
        this.cartItems = cartItems;
    }
}
