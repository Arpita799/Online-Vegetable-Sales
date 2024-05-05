package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Category;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VegetableDao extends JpaRepository<Vegetable,Integer> {
    List<Vegetable> findByCategory(Category category);
    Vegetable findByName(String name);
}
