package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public void Register(@RequestBody User user){
        user.setAdmin(false);
        user.setGroupId(null);
        service.Save(user);
    }

    @PostMapping("/login")
    public boolean Login(@RequestBody User user){

        if(service.DoesUserExist(user.getUsername())){
            //authenticate user
            return true;
        }
        return false;
    }
}
