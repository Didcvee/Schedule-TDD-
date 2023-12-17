package ru.didcvee.raspisanye.entity;

import java.util.Objects;

public class Group {
    private String name;
    private int countPeople;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return countPeople == group.countPeople && Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countPeople);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", countPeople=" + countPeople +
                '}';
    }

    public int getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public Group(String name, int countPeople) {
        this.name = name;
        this.countPeople = countPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
