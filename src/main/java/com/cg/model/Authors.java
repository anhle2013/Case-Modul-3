package com.cg.model;

public class Authors {
    private int id;
    private String name;

    public Authors() {}

    public Authors(String name) {
        this.name = name;
    }

    public Authors(int id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
