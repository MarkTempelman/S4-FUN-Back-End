package com.weekmenu.weekmenu.models;

public class TagAmount {
    private Tag tag;
    private Integer amount;

    public TagAmount() {
        super();
    }

    public TagAmount(Tag tag, Integer amount) {
        this.tag = tag;
        this.amount = amount;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
