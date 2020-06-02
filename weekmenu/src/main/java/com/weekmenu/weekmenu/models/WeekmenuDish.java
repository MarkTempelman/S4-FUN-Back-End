package com.weekmenu.weekmenu.models;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "weekmenu_dish")
public class WeekmenuDish implements Serializable {

    @EmbeddedId
    private WeekmenuDishId id = new WeekmenuDishId();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    private Weekmenu weekmenu;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("dishId")
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private Integer day;

    public WeekmenuDish(){

    }

    public WeekmenuDish(Dish dish){
        this.dish = dish;
    }

//    @JsonIgnore
//    public Weekmenu getWeekmenu() {
//        return weekmenu;
//    }

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
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof WeekmenuDish)) return false;
        WeekmenuDish that = (WeekmenuDish) o;
        return Objects.equals(dish.getId(), that.dish.getId()) &&
                Objects.equals(day, that.day);
    }

    @Override
    public int hashCode(){
        return Objects.hash(dish.getId(), day);
    }
}
