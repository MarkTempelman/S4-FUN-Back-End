package com.weekmenu.weekmenu.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Integer id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ingredient")
    private Set<DishIngredient> dishes = new HashSet<>();

    public Ingredient(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

//    public Set<DishIngredient> getDishes() {
//        return dishes;
//    }

    public void setDishes(Set<DishIngredient> dishes) {
        this.dishes = dishes;
    }

    public void addDishIngredient(DishIngredient dishIngredient){
        if(dishes.contains(dishIngredient))
            return;

        dishes.add(dishIngredient);

        dishIngredient.setIngredient(this);
    }

    public void removeDishIngredient(DishIngredient dishIngredient){
        if(!dishes.contains(dishIngredient))
            return;

        dishes.remove(dishIngredient);

        dishIngredient.setIngredient(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Ingredient ingredient = (Ingredient) obj;
        return Objects.equals(id, ingredient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
