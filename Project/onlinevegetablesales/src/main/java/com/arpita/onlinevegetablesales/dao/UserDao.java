package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String userName);

}
