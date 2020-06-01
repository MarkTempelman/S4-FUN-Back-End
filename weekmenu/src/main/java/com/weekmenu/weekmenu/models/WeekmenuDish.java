package com.weekmenu.weekmenu.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity

public class WeekmenuDish implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Dish dish;

    private Integer day;

    public WeekmenuDish(){

    }

    public WeekmenuDish(Dish dish){
        this.dish = dish;
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
