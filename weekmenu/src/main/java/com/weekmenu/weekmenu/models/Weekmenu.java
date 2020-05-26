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

    @OneToMany(
            mappedBy = "weekmenu",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<WeekmenuDish> weekmenuDishList = new ArrayList<>();

    public Weekmenu(){}

    public Weekmenu(Integer groupId, Date startDate) {
        this.groupId = groupId;
        this.startDate = startDate;
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

    public List<WeekmenuDish> getWeekmenuDishList() {
        return weekmenuDishList;
    }

    public void setWeekmenuDishList(List<WeekmenuDish> weekmenuDishList) {
        this.weekmenuDishList = weekmenuDishList;
    }

    public void addDish(Dish dish){
        WeekmenuDish weekmenuDish = new WeekmenuDish(this, dish);
        weekmenuDishList.add(weekmenuDish);
        dish.getWeekmenuDishList().add(weekmenuDish);
    }

    public void removeDish(Dish dish){
        for(Iterator<WeekmenuDish> iterator = weekmenuDishList.iterator();
            iterator.hasNext(); ) {
            WeekmenuDish weekmenuDish = iterator.next();

            if(weekmenuDish.getWeekmenu().equals(this) && weekmenuDish.getDish().equals(dish)) {
                iterator.remove();
                weekmenuDish.getDish().getWeekmenuDishList().remove(weekmenuDish);
                weekmenuDish.setWeekmenu(null);
                weekmenuDish.setDish(null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Weekmenu that = (Weekmenu) obj;
        return Objects.equals(groupId, that.getGroupId()) &&
                Objects.equals(startDate, that.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, startDate);
    }
}
