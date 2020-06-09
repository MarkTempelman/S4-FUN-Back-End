package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.WeekmenuRepository;
import com.weekmenu.weekmenu.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class WeekmenuServiceTest {

    @Autowired
    private WeekmenuRepository weekmenuRepository;

    private WeekmenuService weekmenuService = new WeekmenuService(weekmenuRepository);

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateWeekmenu() {
        //arrange
        Tag tag1 = new Tag(1, 1, "tag1");
        Set<Tag> tag1Set = new HashSet<>();
        tag1Set.add(tag1);
        Tag tag2 = new Tag(2, 1, "tag2");
        Set<Tag> tag2Set = new HashSet<>();
        tag2Set.add(tag2);
        Tag tag3 = new Tag(3, 1, "tag3");
        Set<Tag> tag3Set = new HashSet<>();
        tag3Set.add(tag3);

        Dish dish1 = new Dish(1, 1, "dish1", "dish desc");
        dish1.setTags(tag1Set);
        Dish dish2 = new Dish(2, 1, "dish2", "dish desc");
        dish2.setTags(tag1Set);
        Dish dish3 = new Dish(3, 1, "dish3", "dish desc");
        dish3.setTags(tag2Set);
        Dish dish4 = new Dish(4, 1, "dish4", "dish desc");
        dish4.setTags(tag2Set);
        Dish dish5 = new Dish(5, 1, "dish5", "dish desc");
        dish5.setTags(tag3Set);
        Dish dish6 = new Dish(6, 1, "dish6", "dish desc");
        dish6.setTags(tag3Set);
        Dish dish7 = new Dish(7, 1, "dish7", "dish desc");
        dish7.setTags(tag3Set);

        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(dish3);
        dishes.add(dish4);
        dishes.add(dish5);
        dishes.add(dish6);
        dishes.add(dish7);

        //act
        dishes = weekmenuService.getDishesByTag(dishes, tag1);

        //assert
        assertEquals(2, dishes.size());
        for(Dish dish : dishes){
            assertTrue(dish.getTags().contains(tag1));
        }
    }
}