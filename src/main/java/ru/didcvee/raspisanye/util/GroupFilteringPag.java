package ru.didcvee.raspisanye.util;

public class GroupFilteringPag {
    int page;
    int size;
    String nameGroup;

    String sort;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public GroupFilteringPag(int page, int size, String nameGroup, String sort) {
        this.page = page;
        this.size = size;
        this.nameGroup = nameGroup;
        this.sort = sort;
    }
}
