package com.weekmenu.weekmenu.models;

import com.fasterxml.jackson.annotation.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "weekmenu")
    private Set<WeekmenuDish> weekmenuDishList = new HashSet<>();

    public Weekmenu(){}

    public Weekmenu(Integer groupId, Date startDate, Set<WeekmenuDish> weekmenuDishes) {
        this.groupId = groupId;
        this.startDate = startDate;
        this.weekmenuDishList = weekmenuDishes;
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

    public Set<WeekmenuDish> getWeekmenuDishList() {
        return weekmenuDishList;
    }

    public void setWeekmenuDishList(Set<WeekmenuDish> weekmenuDishList) {
        this.weekmenuDishList = weekmenuDishList;
    }

    public void addDish(Dish dish){
        WeekmenuDish weekmenuDish = new WeekmenuDish(dish);
        weekmenuDishList.add(weekmenuDish);
    }

    public void removeDish(Dish dish){
        for(Iterator<WeekmenuDish> iterator = weekmenuDishList.iterator();
            iterator.hasNext(); ) {
            WeekmenuDish weekmenuDish = iterator.next();

            if(weekmenuDish.getDish().equals(dish)) {
                iterator.remove();
                weekmenuDish.setDish(null);
            }
        }
    }
}
