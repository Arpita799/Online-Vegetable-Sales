package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {
    Role findByName(String role);
}
