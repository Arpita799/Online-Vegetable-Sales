package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Order;
import com.arpita.onlinevegetablesales.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findByOrder(Order order);
}
