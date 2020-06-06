package com.weekmenu.weekmenu.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "weekmenu")
public class Weekmenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_id")
    private Integer id;

    @Column(name="group_id")
    private Integer groupId;

    @Column(name = "weekmenu_start_date")
    private Date startDate = new Date();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "weekmenu", cascade = CascadeType.ALL)
    private Set<WeekmenuDish> weekmenuDishes = new HashSet<>();

    public Weekmenu(){}

    public Weekmenu(Integer groupId, Date startDate, Set<WeekmenuDish> weekmenuDishes) {
        this.groupId = groupId;
        this.startDate = startDate;
        this.weekmenuDishes = weekmenuDishes;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Set<WeekmenuDish> getWeekmenuDishes() {
        return weekmenuDishes;
    }

    public void setWeekmenuDishes(Set<WeekmenuDish> weekmenuDishList) {
        this.weekmenuDishes = weekmenuDishList;
    }

    public void addWeekmenuDish(WeekmenuDish weekmenuDish){
        weekmenuDishes.add(weekmenuDish);
    }

    public void removeDish(Dish dish){
        for(Iterator<WeekmenuDish> iterator = weekmenuDishes.iterator();
            iterator.hasNext(); ) {
            WeekmenuDish weekmenuDish = iterator.next();

            if(weekmenuDish.getDish().equals(dish)) {
                iterator.remove();
                weekmenuDish.setDish(null);
            }
        }
    }
}
