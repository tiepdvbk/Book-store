package com.example.bookstoreapp.CaNhan;

public class CaNhan {
    private int ic;
    private String title;

    public CaNhan(int ic, String title) {
        this.ic = ic;
        this.title = title;
    }

    public CaNhan() {
    }

    public int getIc() {
        return ic;
    }

    public void setIc(int ic) {
        this.ic = ic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
