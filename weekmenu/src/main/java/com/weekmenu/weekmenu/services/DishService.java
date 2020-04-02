package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.DishRepository;
import com.weekmenu.weekmenu.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository repo;

    public List<Dish> listAll() {
        return repo.findAll();
    }

    public void Save(Dish dish){
        repo.save(dish);
    }

    public Dish get(Integer id){
        return repo.findById(id).get();
    }

    public void Delete(Integer id){
        repo.deleteById(id);
    }
}
