package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.CategoryDao;
import com.arpita.onlinevegetablesales.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    public List<Category> getAllCategory(){
        return categoryDao.findAll();
    }
    public Category getCategoryByName(String name){
        return categoryDao.findCategoryByName(name);
    }
}
