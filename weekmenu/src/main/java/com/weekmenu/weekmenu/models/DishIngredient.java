package com.weekmenu.weekmenu.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dish_ingredient")
public class DishIngredient implements Serializable {
//    @EmbeddedId
//    private DishIngredientId id = new DishIngredientId();

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("dishId")
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Integer quantity;

    public DishIngredient() {
    }

    public DishIngredient(Dish dish, Ingredient ingredient, Integer quantity) {
        this.dish = dish;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }


    public void setIngredient(Ingredient ingredient) {
        if(Objects.equals(this.ingredient, ingredient)){
            return;
        }

        Ingredient oldIngredient = this.ingredient;

        this.ingredient = ingredient;
        if(oldIngredient != null){
            oldIngredient.removeDishIngredient(this);
        }

        if(this.ingredient != null){
            this.ingredient.addDishIngredient(this);
        }
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
        return Objects.hash(ingredient.getId());
    }
}
