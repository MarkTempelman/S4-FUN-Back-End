package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.helpers.ServiceHelpers;
import com.weekmenu.weekmenu.interfaces.WeekmenuRepository;
import com.weekmenu.weekmenu.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeekmenuService {
    private final WeekmenuRepository repo;

    @Autowired
    public WeekmenuService(WeekmenuRepository repository){
        repo = repository;
    }

    public List<Weekmenu> getCurrentWeekmenusByGroupId(Integer id){
        Date date = ServiceHelpers.getMondayFromLocalDate(LocalDate.now());
        return this.repo.findWeekmenusByGroupIdAndStartDate(id, date);
    }

    public Weekmenu GenerateWeekmenu(WeekmenuRequirements requirements, List<Dish> dishes){
        Weekmenu weekmenu = new Weekmenu(dishes.get(0).getGroupId(), getNextMonday());
        Set<Dish> weekmenuDishes = new HashSet<>();
        final Dish[] dish = new Dish[1];

        requirements.getTags().forEach((tagAmount)->{
                    for (int i = 0; i < tagAmount.getAmount(); i++){
                        dish[0] = getRandomDishFromDishes(getDishesByTag(dishes, tagAmount.getTag()));
                        weekmenuDishes.add(dish[0]);
                        dishes.remove(dish[0]);
                    }
                });

        return addDishesToWeekmenu(weekmenu, weekmenuDishes);
    }

    public void SaveWeekmenu(Weekmenu weekmenu){
        repo.save(weekmenu);
    }

    private Dish getRandomDishFromDishes(Collection<Dish> dishes){
        Random random = new Random();
        List<Dish> dishList = (List <Dish>) dishes;
        return dishList.get(random.nextInt(dishes.size()));
    }

    private Date getNextMonday(){
        return ServiceHelpers.getMondayFromLocalDate(LocalDate.now().plusDays(7));
    }

    private List<Dish> getDishesByTag(List<Dish> dishes, Tag tag){
        return dishes.stream().filter(dish -> dish.getTags().contains(tag)).collect(Collectors.toList());
    }

    private Weekmenu addDishesToWeekmenu(Weekmenu weekmenu, Set<Dish> weekmenuDishes){
        List<Dish> dishList;
        dishList = new ArrayList<>(weekmenuDishes);

        for(int i = 1; i <= 7; i++){
            weekmenu.addWeekmenuDish(new WeekmenuDish(weekmenu, dishList.get(i - 1), i));
        }
        return weekmenu;
    }
}
