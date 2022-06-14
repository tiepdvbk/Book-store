package com.example.bookstoreapp.DonHang;

public class DonHang {
    private int id;
    private int id_user;
    private String dateTime;
    private double price;
    private String trangThai;

    public DonHang() {
    }
    public DonHang( int id, int id_user, String dateTime, double price, String trangThai) {
        this.id = id;
        this.id_user = id_user;
        this.dateTime = dateTime;
        this.price = price;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
