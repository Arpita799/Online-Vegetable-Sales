package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Cart;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDao extends JpaRepository<Cart,Integer> {
    List<Cart> findByUser(User user);
    Cart findByUserAndVegetable(User user,Vegetable vegetable);
    void deleteByUserAndVegetable(User user, Vegetable vegetable);
    void deleteByUser(User user);
}
