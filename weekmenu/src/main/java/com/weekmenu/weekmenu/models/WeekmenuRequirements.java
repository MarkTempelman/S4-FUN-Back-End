package com.weekmenu.weekmenu.models;

import java.util.ArrayList;
import java.util.List;

public class WeekmenuRequirements {
    List<WeekmenuRequirement> weekmenuRequirements = new ArrayList<>();

    public WeekmenuRequirements() {
        super();
    }

    public List<WeekmenuRequirement> getWeekmenuRequirements() {
        return weekmenuRequirements;
    }

    public void setWeekmenuRequirements(List<WeekmenuRequirement> weekmenuRequirements) {
        this.weekmenuRequirements = weekmenuRequirements;
    }
}
