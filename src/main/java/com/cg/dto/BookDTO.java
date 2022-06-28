package com.cg.dto;

public class BookDTO {
    private int id;
    private String name;
    private String author;
    private String genre;
    private String publisher;
    private int quantity;
    private int available;
    private boolean active;

    public BookDTO() {}

    public BookDTO(int id, String name, String author, String genre, String publisher, int available) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.available = available;
    }

    public BookDTO(int id, String name, String author, String genre, String publisher, int available, boolean active) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.available = available;
        this.active = active;
    }

    public BookDTO(int id, String name, String author, String genre, String publisher, int quantity, int available, boolean active) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.quantity = quantity;
        this.available = available;
        this.active = active;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", quantity=" + quantity +
                ", available=" + available +
                '}';
    }
}
