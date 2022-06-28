package com.cg.model;

public class Publishers {
    private int id;
    private String name;

    public Publishers() {}

    public Publishers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Publishers(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
