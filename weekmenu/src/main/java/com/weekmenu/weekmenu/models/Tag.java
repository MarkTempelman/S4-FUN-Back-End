package com.weekmenu.weekmenu.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer id;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    private Set<Dish> dishes = new HashSet<>();

    public Tag(){

    }

    public Tag(Integer id, Integer groupId, String tagName) {
        this.id = id;
        this.groupId = groupId;
        this.tagName = tagName;
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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Tag tag = (Tag) obj;
        return Objects.equals(id, tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
