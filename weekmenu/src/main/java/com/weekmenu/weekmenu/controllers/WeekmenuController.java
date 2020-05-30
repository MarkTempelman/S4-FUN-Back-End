package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.helpers.ControllerHelpers;
import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.models.Weekmenu;
import com.weekmenu.weekmenu.services.UserService;
import com.weekmenu.weekmenu.services.WeekmenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/member/weekmenu")
public class WeekmenuController {

    private final WeekmenuService weekmenuService;
    private final UserService userService;

    public WeekmenuController(WeekmenuService weekmenuService, UserService userService) {
        this.weekmenuService = weekmenuService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<Weekmenu> GetWeekmenus() {
        User user = ControllerHelpers.GetCurrentUser(userService);
        return weekmenuService.getCurrentWeekmenuByGroupId(user.getGroupId());
    }
}
