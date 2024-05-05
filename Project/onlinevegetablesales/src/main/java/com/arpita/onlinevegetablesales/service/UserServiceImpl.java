package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.RoleDao;
import com.arpita.onlinevegetablesales.dao.UserDao;
import com.arpita.onlinevegetablesales.entity.Role;
import com.arpita.onlinevegetablesales.entity.User;
import com.arpita.onlinevegetablesales.user.UpdateProfile;
import com.arpita.onlinevegetablesales.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserByUserName(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public void save(WebUser webUser) {
        User user = new User();
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());
        user.setUsername(webUser.getUserName());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setEnabled(true);
        user.setRoles(Arrays.asList(roleDao.findByName("ROLE_CUSTOMER")));
        userDao.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void updateUser(int id, UpdateProfile updateProfile) {
        Optional<User> optional = userDao.findById(id);
        User user = optional.get();
        user.setFirstName(updateProfile.getFirstName());
        user.setLastName(updateProfile.getLastName());
        user.setEmail(updateProfile.getEmail());
        userDao.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}
