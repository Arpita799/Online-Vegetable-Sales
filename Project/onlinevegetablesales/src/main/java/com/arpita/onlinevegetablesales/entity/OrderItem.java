package com.arpita.onlinevegetablesales.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="order_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="unit_price")
    private double unitPrice;

    @Column(name="quantity")
    private int quantity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vegetable_id", referencedColumnName = "id")
    private Vegetable vegetable;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
