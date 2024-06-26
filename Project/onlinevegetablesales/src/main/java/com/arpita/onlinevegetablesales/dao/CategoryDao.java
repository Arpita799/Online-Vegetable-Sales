package com.arpita.onlinevegetablesales.dao;

import com.arpita.onlinevegetablesales.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {
    Category findCategoryByName(String name);
}
