package com.weekmenu.weekmenu.interfaces;

import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.models.Weekmenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekmenuRepository extends JpaRepository<Weekmenu, Integer> {
    List<Weekmenu> findWeekmenusByGroupId(Integer GroupId);
}
