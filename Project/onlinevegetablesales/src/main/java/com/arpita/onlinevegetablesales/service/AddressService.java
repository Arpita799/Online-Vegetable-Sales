package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.AddressDao;
import com.arpita.onlinevegetablesales.entity.Address;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.user.WebAddress;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressDao addressDao;
    public Address addAddress(WebAddress webAddress){
        Address address = new Address();
        address.setFullName(webAddress.getFullName());
        address.setPhoneNumber(webAddress.getPhoneNumber());
        address.setStreet(webAddress.getAddress());
        address.setCity(webAddress.getCity());
        address.setState(webAddress.getState());
        address.setCountry(webAddress.getCountry());
        address.setZipCode(webAddress.getPinCode());
        addressDao.save(address);
        return address;
    }
}
