package com.arpita.onlinevegetablesales.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="feedback")
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="feedback")
    private String feedback;
    @Column(name="date_posted")
    @UpdateTimestamp
    private Date datePosted;

    @ManyToOne
    @JoinColumn(name="vegetable_id")
    private Vegetable vegetable;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
