package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressDao extends JpaRepository<Address,Integer> {
}
