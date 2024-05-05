package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.ContactUsDao;
import com.arpita.onlinevegetablesales.entity.ContactUs;
import com.arpita.onlinevegetablesales.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class ContactUsService {
    @Autowired
    private ContactUsDao contactUsDao;
    public void raiseQuery(ContactUs contactUs){
        contactUs.setDatePosted(new Date(System.currentTimeMillis()));
        contactUsDao.save(contactUs);
    }
    public ContactUs getContactDetails(User user){
        ContactUs contactUs = contactUsDao.findByUser(user);
        if(contactUs==null)
            contactUs = new ContactUs();
        return contactUs;
    }
}
