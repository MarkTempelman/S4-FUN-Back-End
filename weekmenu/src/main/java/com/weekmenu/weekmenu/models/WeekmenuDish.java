package com.weekmenu.weekmenu.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name ="weekmenu_dish")
public class WeekmenuDish {

    @EmbeddedId
    private WeekmenuDishId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId")
    private Weekmenu weekmenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dishId")
    private Dish dish;

    @Column(name = "day")
    private Integer day;

    public WeekmenuDish(){

    }

    public WeekmenuDish(Weekmenu weekmenu, Dish dish){
        this.weekmenu = weekmenu;
        this.dish = dish;
        this.id = new WeekmenuDishId(weekmenu.getId(), dish.getId());
    }

    public WeekmenuDishId getId() {
        return id;
    }

    public void setId(WeekmenuDishId id) {
        this.id = id;
    }

    public Weekmenu getWeekmenu() {
        return weekmenu;
    }

    public void setWeekmenu(Weekmenu weekmenu) {
        this.weekmenu = weekmenu;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        WeekmenuDish that = (WeekmenuDish) obj;
        return Objects.equals(weekmenu, that.weekmenu) &&
                Objects.equals(dish, that.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekmenu, dish);
    }
}
