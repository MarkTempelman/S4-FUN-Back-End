package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.helpers.ControllerHelpers;
import com.weekmenu.weekmenu.models.Weekmenu;
import com.weekmenu.weekmenu.models.WeekmenuDish;
import com.weekmenu.weekmenu.models.WeekmenuRequirements;
import com.weekmenu.weekmenu.services.DishService;
import com.weekmenu.weekmenu.services.UserService;
import com.weekmenu.weekmenu.services.WeekmenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Weekmenu> getWeekmenu() {
        List<Weekmenu> weekmenus = weekmenuService.getCurrentWeekmenusByGroupId(ControllerHelpers.GetCurrentGroupId(userService));
        return ControllerHelpers.sortDishesInWeekmenus(weekmenus);
    }

    @GetMapping("/member/weekmenu/next")
    public List<Weekmenu> getNextWeekmenu() {
        List<Weekmenu> weekmenus = weekmenuService.getNextWeekmenusByGroupId(ControllerHelpers.GetCurrentGroupId(userService));
        return ControllerHelpers.sortDishesInWeekmenus(weekmenus);
    }

    @PostMapping("/admin/weekmenu/create")
    public ResponseEntity generateWeekmenu(@RequestBody WeekmenuRequirements weekmenuRequirements){
        if(weekmenuService.doesNextWeekmenuExist(ControllerHelpers.GetCurrentGroupId(userService))){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        weekmenuService.SaveWeekmenu(weekmenuService.GenerateWeekmenu(
                weekmenuRequirements,
                dishService.GetDishesByGroupId(ControllerHelpers.GetCurrentGroupId(userService))
        ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/member/weekmenu/next-weekmenu-exist")
    public boolean doesNextWeekmenuExist(){
        return weekmenuService.doesNextWeekmenuExist(ControllerHelpers.GetCurrentGroupId(userService));
    }
}
