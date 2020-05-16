package com.weekmenu.weekmenu.interfaces;

import com.weekmenu.weekmenu.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findDishesByGroupId(Integer GroupId);
}
