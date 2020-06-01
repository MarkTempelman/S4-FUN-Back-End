package com.weekmenu.weekmenu.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WeekmenuDishId implements Serializable {

    private Integer menuId;
    private Integer dishId;

    public WeekmenuDishId() {}

    public WeekmenuDishId(Integer menuId, Integer dishId){
        super();
        this.dishId = dishId;
        this.menuId = menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;

        if (object == null || getClass() != object.getClass())
            return false;

        WeekmenuDishId that = (WeekmenuDishId) object;
        return Objects.equals(dishId, that.dishId) && Objects.equals(menuId, that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, menuId);
    }
}
