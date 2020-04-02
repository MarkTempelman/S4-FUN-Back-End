package com.weekmenu.weekmenu.interfaces;

import com.weekmenu.weekmenu.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
