package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.helpers.ControllerHelpers;
import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.models.DishIngredient;
import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.models.WeekmenuDish;
import com.weekmenu.weekmenu.services.DishService;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping()
public class DishController {

    private final DishService service;
    private final UserService userService;

    public DishController(DishService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/member/dishes")
    public List<Dish> List(){
        User user = ControllerHelpers.GetCurrentUser(userService);
        return service.GetDishesByGroupId(user.getGroupId());
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

    @PostMapping("/admin/dishes/create")
    public void Add(@RequestBody Dish dish){
        dish.setGroupId(ControllerHelpers.GetCurrentGroupId(userService));
        List<DishIngredient> dishIngredients = new ArrayList<>();
        dish.getIngredients().forEach(dishIngredient -> dishIngredients.add(new DishIngredient(dish, dishIngredient.getIngredient(), dishIngredient.getQuantity())));
        dish.setIngredients(dishIngredients);
        service.saveDish(dish);
    }

//    @PutMapping("/dishes/{id}")
//    public ResponseEntity Update(@RequestBody Dish dish, @PathVariable Integer id){
//        try {
//            Dish existDish = service.get(id);
//            service.saveDish(dish);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NoSuchElementException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
