package com.example.bookstoreapp.DanhMuc;

public class DanhMuc {
    private int id;
    private String name;

    public DanhMuc(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DanhMuc(String name) {
        this.name = name;
    }

    public DanhMuc() {
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
