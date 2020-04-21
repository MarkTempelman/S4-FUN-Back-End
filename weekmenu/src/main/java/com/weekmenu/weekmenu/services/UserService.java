package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.UserRepository;
import com.weekmenu.weekmenu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void Save(User user){
        repo.save(user);
    }

    public User Get(String name){
        return repo.findByUsername(name);
    }

    public boolean DoesUserExist(String name){
        return repo.existsUserByUsername(name);
    }
}
