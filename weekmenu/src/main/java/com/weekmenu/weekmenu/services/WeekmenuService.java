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
    public WeekmenuService(WeekmenuRepository repository) {
        repo = repository;
    }

    public Boolean doesNextWeekmenuExist(Integer groupId) {
        if (repo.howManyWeekmenusWithGroupIdAndStartDate(groupId, getNextMonday()) > 0) {
            return true;
        }
        return false;
    }

    public List<Weekmenu> getCurrentWeekmenusByGroupId(Integer id) {
        Date date = ServiceHelpers.getMondayFromLocalDate(LocalDate.now());
        return this.repo.findWeekmenusByGroupIdAndStartDate(id, date);
    }

    public List<Weekmenu> getNextWeekmenusByGroupId(Integer id) {
        return this.repo.findWeekmenusByGroupIdAndStartDate(id, getNextMonday());
    }

    // needs to be refactored
    public Weekmenu GenerateWeekmenu(List<WeekmenuRequirement> requirements, List<Dish> dishes) {
        Weekmenu weekmenu = new Weekmenu(dishes.get(0).getGroupId(), getNextMonday());
        List<Dish> tempDishes;
        Set<Dish> weekmenuDishes = new HashSet<>();

        for (WeekmenuRequirement requirement : requirements) {
            if (requirement.getTag().getId() != null && requirement.getIngredient().getId() != null) {
                tempDishes = getDishesByTagAndIngredient(requirement.getTag(), requirement.getIngredient(), dishes);
                if (tempDishes.size() > 0) {
                    Dish dish = getRandomDishFromDishes(tempDishes);
                    weekmenuDishes.add(dish);
                    dishes.remove(dish);
                    tempDishes.clear();
                } else {
                    tempDishes = getDishesByIngredient(dishes, requirement.getIngredient());
                    if (tempDishes.size() > 0) {
                        Dish dish = getRandomDishFromDishes(tempDishes);
                        weekmenuDishes.add(dish);
                        dishes.remove(dish);
                        tempDishes.clear();
                    } else {
                        tempDishes = getDishesByTag(dishes, requirement.getTag());
                        if (tempDishes.size() > 0) {
                            Dish dish = getRandomDishFromDishes(tempDishes);
                            weekmenuDishes.add(dish);
                            dishes.remove(dish);
                            tempDishes.clear();
                        } else {
                            Dish dish = getRandomDishFromDishes(dishes);
                            weekmenuDishes.add(dish);
                            dishes.remove(dish);
                            tempDishes.clear();
                        }
                    }
                }
            } else if (requirement.getTag().getId() != null && requirement.getIngredient().getId() == null){
                tempDishes = getDishesByTag(dishes, requirement.getTag());
                if (tempDishes.size() > 0) {
                    Dish dish = getRandomDishFromDishes(tempDishes);
                    weekmenuDishes.add(dish);
                    dishes.remove(dish);
                    tempDishes.clear();
                } else {
                    Dish dish = getRandomDishFromDishes(dishes);
                    weekmenuDishes.add(dish);
                    dishes.remove(dish);
                    tempDishes.clear();
                }
            } else if (requirement.getTag().getId() != null && requirement.getIngredient().getId() == null){
                tempDishes = getDishesByIngredient(dishes, requirement.getIngredient());
                if (tempDishes.size() > 0) {
                    Dish dish = getRandomDishFromDishes(tempDishes);
                    weekmenuDishes.add(dish);
                    dishes.remove(dish);
                    tempDishes.clear();
                } else {
                    Dish dish = getRandomDishFromDishes(dishes);
                    weekmenuDishes.add(dish);
                    dishes.remove(dish);
                    tempDishes.clear();
                }
            }
            else {
                Dish dish = getRandomDishFromDishes(dishes);
                weekmenuDishes.add(dish);
                dishes.remove(dish);
            }
        }
        return addDishesToWeekmenu(weekmenu, weekmenuDishes);
    }


    public void SaveWeekmenu(Weekmenu weekmenu) {
        repo.save(weekmenu);
    }

    public List<Dish> getDishesByTagAndIngredient(Tag tag, Ingredient ingredient, List<Dish> dishes) {
        dishes = getDishesByTag(dishes, tag);
        dishes = getDishesByIngredient(dishes, ingredient);
        return dishes;
    }

    private Dish getRandomDishFromDishes(Collection<Dish> dishes) {
        Random random = new Random();
        List<Dish> dishList = (List<Dish>) dishes;
        return dishList.get(random.nextInt(dishes.size()));
    }

    public Date getNextMonday() {
        return ServiceHelpers.getMondayFromLocalDate(LocalDate.now().plusDays(7));
    }

    public List<Dish> getDishesByTag(List<Dish> dishes, Tag tag) {
        return dishes.stream().filter(dish -> dish.getTags().contains(tag)).collect(Collectors.toList());
    }

    public List<Dish> getDishesByIngredient(List<Dish> dishes, Ingredient ingredient) {
        boolean doesDishContainIngredient;
        for (Dish dish : dishes) {
            doesDishContainIngredient = false;
            for (DishIngredient dishIngredient : dish.getIngredients()) {
                if (dishIngredient.getIngredient() == ingredient) {
                    doesDishContainIngredient = true;
                }
            }
            if (!doesDishContainIngredient) {
                dishes.remove(dish);
            }
        }
        return dishes;
    }

    public Weekmenu addDishesToWeekmenu(Weekmenu weekmenu, Set<Dish> weekmenuDishes) {
        List<Dish> dishList;
        dishList = new ArrayList<>(weekmenuDishes);

        for (int i = 1; i <= 7; i++) {
            weekmenu.addWeekmenuDish(new WeekmenuDish(weekmenu, dishList.get(i - 1), i));
        }
        return weekmenu;
    }
}
