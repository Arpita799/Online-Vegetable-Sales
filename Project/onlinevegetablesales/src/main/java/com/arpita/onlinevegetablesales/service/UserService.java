package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.user.UpdateProfile;
import com.arpita.onlinevegetablesales.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;


public interface UserService extends UserDetailsService {
    public User getUserByUserName(String userName);
    public void save(WebUser webUser);
    public void deleteUser(User user);
    public void updateUser(int id, UpdateProfile updateProfile);
}
