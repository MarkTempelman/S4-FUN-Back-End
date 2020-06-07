package com.weekmenu.weekmenu.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dish_ingredient")
public class DishIngredient {
    @EmbeddedId
    private DishIngredientId id = new DishIngredientId();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("dishId")
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Integer quantity;

    public DishIngredient() {
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof DishIngredient)) return false;
        DishIngredient that = (DishIngredient) o;
        return Objects.equals(dish.getId(), that.dish.getId()) &&
                Objects.equals(ingredient.getId(), that.ingredient.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(dish.getId(), ingredient.getId());
    }
}
