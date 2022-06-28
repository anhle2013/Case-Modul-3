package com.cg.model;

public class Class {
    private int id;
    private String name;
    private String deparment;

    public Class() {}

    public Class(int id, String name, String deparment) {
        this.id = id;
        this.name = name;
        this.deparment = deparment;
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

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }
}
