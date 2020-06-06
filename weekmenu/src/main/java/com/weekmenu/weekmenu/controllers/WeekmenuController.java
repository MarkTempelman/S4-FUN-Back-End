package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.helpers.ControllerHelpers;
import com.weekmenu.weekmenu.models.Weekmenu;
import com.weekmenu.weekmenu.models.WeekmenuDish;
import com.weekmenu.weekmenu.models.WeekmenuRequirements;
import com.weekmenu.weekmenu.services.DishService;
import com.weekmenu.weekmenu.services.UserService;
import com.weekmenu.weekmenu.services.WeekmenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class WeekmenuController {

    private final WeekmenuService weekmenuService;
    private final UserService userService;
    private final DishService dishService;

    public WeekmenuController(WeekmenuService weekmenuService, UserService userService, DishService dishService) {
        this.weekmenuService = weekmenuService;
        this.userService = userService;
        this.dishService = dishService;
    }

    @GetMapping("/member/weekmenu/current")
    public List<Weekmenu> GetWeekmenu() {
        List<Weekmenu> weekmenus = weekmenuService.getCurrentWeekmenusByGroupId(ControllerHelpers.GetCurrentGroupId(userService));
        weekmenus.forEach(weekmenu -> {
            Set<WeekmenuDish> weekmenuDishes = weekmenu.getWeekmenuDishes();
            weekmenuDishes = ControllerHelpers.sortWeekmenuDishesByDay(weekmenuDishes);
            weekmenu.setWeekmenuDishes(weekmenuDishes);
        });
        return weekmenus;
    }

    @PostMapping("/admin/weekmenu/create")
    public void GenerateWeekmenu(@RequestBody WeekmenuRequirements weekmenuRequirements){
        System.out.println("requirements received");
        weekmenuService.SaveWeekmenu(weekmenuService.GenerateWeekmenu(
                weekmenuRequirements,
                dishService.GetDishesByGroupId(ControllerHelpers.GetCurrentGroupId(userService))
        ));
    }
}
