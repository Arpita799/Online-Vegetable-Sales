package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.OrderItemDao;
import com.arpita.onlinevegetablesales.entity.Order;
import com.arpita.onlinevegetablesales.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderItemService{
    @Autowired
    private OrderItemDao orderItemDao;
    public void addOrderItem(OrderItem orderItem){
        orderItemDao.save(orderItem);
    }
    public List<OrderItem> getAllOrderItems(Order order){
        return orderItemDao.findByOrder(order);
    }
}
