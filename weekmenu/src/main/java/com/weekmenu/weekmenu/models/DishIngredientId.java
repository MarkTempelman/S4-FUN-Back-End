package com.weekmenu.weekmenu.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DishIngredientId implements Serializable {
    private Integer dishId;
    private Integer ingredientId;

    public DishIngredientId() {
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;

        if (object == null || getClass() != object.getClass())
            return false;

        DishIngredientId that = (DishIngredientId) object;
        return Objects.equals(dishId, that.dishId) && Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, ingredientId);
    }
}
