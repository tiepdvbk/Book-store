package com.example.bookstoreapp.Book;

public class Book {
    private  int pic;
    private String title;
    private String price;
    private String giamGia;

    public Book() {
    }

    public Book(int pic, String title, String price, String giamGia) {
        this.pic = pic;
        this.title = title;
        this.price = price;
        this.giamGia = giamGia;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(String giamGia) {
        this.giamGia = giamGia;
    }
}
