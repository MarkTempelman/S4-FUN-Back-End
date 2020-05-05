package com.weekmenu.weekmenu.interfaces;

import com.weekmenu.weekmenu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsUserByUsername (String username);
}