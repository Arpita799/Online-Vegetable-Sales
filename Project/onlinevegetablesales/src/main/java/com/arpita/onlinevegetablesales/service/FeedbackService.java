package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.FeedbackDao;
import com.arpita.onlinevegetablesales.entity.Feedback;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;
    public Feedback getFeedback(User user, Vegetable vegetable){
        Feedback feedback = feedbackDao.findByUserAndVegetable(user,vegetable);
        if(feedback==null)
            feedback = new Feedback();
        return feedback;
    }
    public void addFeedback(Feedback feedback){
        feedback.setDatePosted(new Date(System.currentTimeMillis()));
        feedbackDao.save(feedback);
    }
    public List<Feedback> getFeedbackByVegetable(Vegetable vegetable){
        List<Feedback> feedbacks = new ArrayList<>();
        if(vegetable!=null){
            feedbacks = feedbackDao.findByVegetable(vegetable);
        }
        return feedbacks;
    }
}
