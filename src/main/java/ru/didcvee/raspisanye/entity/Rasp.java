package ru.didcvee.raspisanye.entity;

import ru.didcvee.raspisanye.entity.Item;

import java.time.DayOfWeek;
import java.util.Date;

public class Rasp {
    private int ordered;
    private String group;

    public Rasp(int ordered, String group, DayOfWeek day, Item item, Date date) {
        this.ordered = ordered;
        this.group = group;
        this.day = day;
        this.item = item;
        this.date = date;
    }

    private DayOfWeek day;
    private Item item;
    private Date date;

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
