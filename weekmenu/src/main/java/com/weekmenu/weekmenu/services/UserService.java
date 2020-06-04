package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.UserRepository;
import com.weekmenu.weekmenu.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public void Save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    public User Get(String name){
        return repo.findByUsername(name);
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public boolean DoesUserExist(String name){
        return repo.existsUserByUsername(name);
    }
}
