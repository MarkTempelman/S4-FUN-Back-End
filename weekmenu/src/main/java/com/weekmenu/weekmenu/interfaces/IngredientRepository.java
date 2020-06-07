package com.weekmenu.weekmenu.interfaces;

import com.weekmenu.weekmenu.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    List<Ingredient> findIngredientsByGroupId(Integer groupId);

    boolean existsIngredientByIngredientName(String ingredientName);

}
