package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    //curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"name\",\"password\":\"password\"}" http://localhost:8080/all/register -v
    @PostMapping("/all/register")
    public void Register(@RequestBody User user){
        user.setAdmin(false);
        user.setGroupId(null);
        service.Save(user);
    }

    @PostMapping("/all/login")
    public boolean Login(@RequestBody User user){

        if(service.DoesUserExist(user.getUsername())){
            //authenticate user
            return true;
        }
        return false;
    }

    //temporary
    @GetMapping("/admin/users")
    public List<User> List(){
        return service.listAll();
    }
}
