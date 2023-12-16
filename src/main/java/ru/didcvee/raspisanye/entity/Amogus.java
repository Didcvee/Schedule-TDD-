package ru.didcvee.raspisanye.entity;

import java.util.Date;
import java.util.Objects;

public class Amogus {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amogus amogus = (Amogus) o;
        return s == amogus.s && Objects.equals(va, amogus.va) && Objects.equals(as, amogus.as) && Objects.equals(item, amogus.item) && Objects.equals(date, amogus.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, va, as, item, date);
    }

    private int s;
    private String va;
    private String as;
    private String item;
    private Date date;

    public Amogus(int s, String va, String as, String item, Date date) {
        this.s = s;
        this.va = va;
        this.as = as;
        this.item = item;
        this.date = date;
    }

    public Amogus() {
    }

    @Override
    public String toString() {
        return "Amogus{" +
                "s=" + s +
                ", va='" + va + '\'' +
                ", as='" + as + '\'' +
                ", item='" + item + '\'' +
                ", date=" + date +
                '}';
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String getVa() {
        return va;
    }

    public void setVa(String va) {
        this.va = va;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
