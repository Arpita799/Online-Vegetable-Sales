package com.arpita.onlinevegetablesales.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="enabled")
    private boolean enabled;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @OneToMany(mappedBy="user",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Order> orders;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


    public void addOrder(Order order){
        if (order != null) {
            if (orders == null) {
                orders = new ArrayList<>();
            }
            orders.add(order);
            order.setUser(this);
        }

    }
    public void addFeedback(Feedback feedback){
        if (feedback != null) {
            if (feedbacks == null) {
                feedbacks = new ArrayList<>();
            }
            feedbacks.add(feedback);
            feedback.setUser(this);
        }

    }
}
