package com.weekmenu.weekmenu.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.*;

@Entity
@Table(name="dish")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dish_id")
    private Integer id;
    @Column(name="group_id")
    private Integer groupId;
    @NaturalId
    @Column(name="dish_name")
    private String name;
    @Column(name="dish_description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dish")
    private Set<WeekmenuDish> weekmenuDishes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name="tag_dish",
        joinColumns = {@JoinColumn(name = "dish_id")},
        inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    public Dish() {
    }

    public Dish(String name, String description, Integer groupId) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
    }

    public Dish(Integer id, Integer groupId, String name, String description) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Dish dish = (Dish) obj;
        return Objects.equals(name, dish.getName()) &&
                Objects.equals(id, dish.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
