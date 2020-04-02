package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishController {

    @Autowired
    private DishService service;

    @GetMapping("/products")
    public List<Dish> list(){
        return service.listAll();
    }
}
