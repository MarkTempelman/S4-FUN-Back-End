package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class DishController {

    @Autowired
    private DishService service;

    @GetMapping("/member/dishes")
    public List<Dish> List(){
        return service.listAll();
    }

    @GetMapping("/member/dishes/{id}")
    public ResponseEntity<Dish> Get(@PathVariable Integer id) {
        try {
            Dish dish = service.get(id);
            return new ResponseEntity<Dish>(dish, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Dish>(HttpStatus.NOT_FOUND);
        }
    }

    //curl -X POST -H "Content-Type: application/json" -d "{\"groupId\":1,\"name\":\"dishName2\",\"description\":\"test2\"}" http://localhost:8080/dishes -v
    @PostMapping("/member/dishes")
    public void Add(@RequestBody Dish dish){
        service.Save(dish);
    }

    //curl -X PUT -H "Content-Type: application/json" -d "{\"id\":1,\"groupId\":1,\"name\":\"updatedName\",\"description\":\"updated desc\"}" http://localhost:8080/dishes/1 -v
    @PutMapping("/member/dishes/{id}")
    public ResponseEntity Update(@RequestBody Dish dish, @PathVariable Integer id){
        try {
            Dish existDish = service.get(id);
            service.Save(dish);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //curl -X DELETE http://localhost:8080/dishes/2 -v
    @DeleteMapping("/member/dishes/{id}")
    public ResponseEntity Delete(@PathVariable Integer id){
        try {
            Dish existDish = service.get(id);
            service.Delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
