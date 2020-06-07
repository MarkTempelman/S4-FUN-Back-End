package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.IngredientRepository;
import com.weekmenu.weekmenu.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredientsByGroupId(Integer id){
        return this.ingredientRepository.findIngredientsByGroupId(id);
    }

    public boolean doesIngredientExist(String ingredientName){
        return ingredientRepository.existsIngredientByIngredientName(ingredientName);
    }

    public void saveIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
}
