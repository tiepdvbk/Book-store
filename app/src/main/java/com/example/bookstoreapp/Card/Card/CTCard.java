package com.example.bookstoreapp.Card.Card;

public class CTCard {
    private int id;
    private int id_card;
    private int id_book;
    private String image_url;
    private String name;
    private Double price;
    private int sl;
    private double total_price;

    public CTCard() {
    }

    public CTCard(int id, int id_card, int id_book, String image_url, String name, Double price, int sl, double total_price) {
        this.id = id;
        this.id_card = id_card;
        this.id_book = id_book;
        this.image_url = image_url;
        this.name = name;
        this.price = price;
        this.sl = sl;
        this.total_price = total_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
