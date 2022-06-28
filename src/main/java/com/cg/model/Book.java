package com.cg.model;

public class Book {
    private int id;
    private String name;
    private int authorId;
    private int genreId;
    private int publisherId;
    private int quantity;
    private int available;
    private boolean active;

    public Book() {}

    public Book(String name, int authorId, int genreId, int publisherId, int quantity, int available) {
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.available = available;
        this.active = true;
    }

    public Book(int id, String name, int authorId, int genreId, int publisherId, int quantity, int available) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.available = available;
    }

    public Book(int id, String name, int authorId, int genreId, int publisherId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
        this.publisherId = publisherId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
