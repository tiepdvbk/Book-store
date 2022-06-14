package com.example.bookstoreapp.Card.Card;

public class Card {
    private int id;
    private int id_user;
    private int bia;
    private String tieuDe, gia;
    private int sl;

    public Card() {
    }

    public Card(int bia, String tieuDe, String gia, int sl) {
        this.bia = bia;
        this.tieuDe = tieuDe;
        this.gia = gia;
        this.sl = sl;
    }

    public int getBia() {
        return bia;
    }

    public void setBia(int bia) {
        this.bia = bia;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
