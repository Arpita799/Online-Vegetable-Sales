package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Feedback;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackDao extends JpaRepository<Feedback,Integer> {
    Feedback findByUserAndVegetable(User user, Vegetable vegetable);
    List<Feedback> findByVegetable(Vegetable vegetable);
}
