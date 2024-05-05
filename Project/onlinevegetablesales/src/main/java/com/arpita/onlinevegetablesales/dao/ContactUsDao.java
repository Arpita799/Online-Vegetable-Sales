package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.ContactUs;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.service.ContactUsService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsDao extends JpaRepository<ContactUs,Integer> {
    ContactUs findByUser(User user);
}
