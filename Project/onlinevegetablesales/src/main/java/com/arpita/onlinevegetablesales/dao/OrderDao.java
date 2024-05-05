package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Order;
import com.arpita.onlinevegetablesales.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {
    List<Order> findOrderByUser(User user);
    Order findById(int id);
}
