package com.weekmenu.weekmenu.models;

import java.util.ArrayList;
import java.util.List;

public class WeekmenuRequirements {
    List<TagAmount> tags = new ArrayList<>();

    public WeekmenuRequirements(List<TagAmount> tags) {
        this.tags = tags;
    }

    public WeekmenuRequirements() {
        super();
    }

    public List<TagAmount> getTags() {
        return tags;
    }

    public void setTags(List<TagAmount> tags) {
        this.tags = tags;
    }
}
