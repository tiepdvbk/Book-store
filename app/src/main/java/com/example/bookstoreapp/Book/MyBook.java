package com.example.bookstoreapp.Book;

public class MyBook {
    private  int id;
    private String name;
    private double price;
    private double gprice;
    private int id_tl;
    private String author;
    private String url_img;

    public MyBook(int id, String name, double price, double gprice, String url_img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gprice = gprice;
        this.url_img = url_img;
    }

    public MyBook(int id, String name, double price, double gprice, int id_tl, String author, String url_img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gprice = gprice;
        this.id_tl = id_tl;
        this.author = author;
        this.url_img = url_img;
    }

    public MyBook(String name, double price, double gprice, String url_img) {
        this.name = name;
        this.price = price;
        this.gprice = gprice;
        this.url_img = url_img;
    }

    public MyBook() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(float gprice) {
        this.gprice = gprice;
    }

    public int getId_tl() {
        return id_tl;
    }

    public void setId_tl(int id_tl) {
        this.id_tl = id_tl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
