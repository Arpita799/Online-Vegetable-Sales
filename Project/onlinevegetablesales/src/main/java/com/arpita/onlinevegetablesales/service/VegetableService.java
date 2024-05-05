package com.arpita.onlinevegetablesales.service;

import com.arpita.onlinevegetablesales.dao.VegetableDao;
import com.arpita.onlinevegetablesales.entity.Category;
import com.arpita.onlinevegetablesales.entity.Vegetable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VegetableService {
    @Autowired
    private VegetableDao vegetableDao;
    public void addVegetable(Vegetable vegetable){
        vegetableDao.save(vegetable);
    }
    public List<Vegetable> getVegetableByCategory(Category category){
        return vegetableDao.findByCategory(category);
    }
    public Vegetable getVegetableByName(String name){
        return vegetableDao.findByName(name);
    }
}
