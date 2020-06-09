package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.DishRepository;
import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.models.DishIngredient;
import com.weekmenu.weekmenu.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DishService {

    private final DishRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public DishService(DishRepository repo) {
        this.repo = repo;
    }

    public List<Dish> GetDishesByGroupId(Integer id){
        return this.repo.findDishesByGroupId(id);
    }

    public List<Dish> listAll() {
        return repo.findAll();
    }

    @Transactional
    public void saveDish(Dish dish){
        Set<DishIngredient> ingredients = new HashSet<>();
//        dish.getIngredients().forEach((i) ->
//                i.setIngredient(entityManager.merge(i.getIngredient()))
//        );

        repo.save(dish);
    }

    public Dish get(Integer id){
        return repo.findById(id).get();
    }

    public void Delete(Integer id){
        repo.deleteById(id);
    }
}
